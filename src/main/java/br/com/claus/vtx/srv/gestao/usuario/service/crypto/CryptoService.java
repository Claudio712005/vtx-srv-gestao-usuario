package br.com.claus.vtx.srv.gestao.usuario.service.crypto;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
@Service
public class CryptoService {

    private static final String TRANSFORMACAO_AES = "AES/CBC/PKCS5Padding";
    private static final String ALGORITMO_HMAC = "HmacSHA256";
    private static final int TAMANHO_CHAVE = 32;
    private static final int TAMANHO_IV = 16;

    private final SecretKeySpec chaveAes;
    private final SecretKeySpec chaveHmac;
    private final SecureRandom secureRandom = new SecureRandom();

    public CryptoService(@Value("${app.crypto.documento.key:}") String chaveBase64) {
        byte[] chave = resolverChave(chaveBase64);
        this.chaveAes = new SecretKeySpec(chave, "AES");
        this.chaveHmac = new SecretKeySpec(derivarChaveHmac(chave), ALGORITMO_HMAC);
    }

    public DadoCifrado cifrar(String texto) {
        try {
            byte[] iv = new byte[TAMANHO_IV];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMACAO_AES);
            cipher.init(Cipher.ENCRYPT_MODE, chaveAes, new IvParameterSpec(iv));
            byte[] conteudo = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));

            return new DadoCifrado(conteudo, iv);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Falha ao cifrar dado sensivel", e);
        }
    }

    public String decifrar(byte[] conteudo, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMACAO_AES);
            cipher.init(Cipher.DECRYPT_MODE, chaveAes, new IvParameterSpec(iv));
            return new String(cipher.doFinal(conteudo), StandardCharsets.UTF_8);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Falha ao decifrar dado sensivel", e);
        }
    }

    public byte[] indiceCego(String texto) {
        try {
            Mac mac = Mac.getInstance(ALGORITMO_HMAC);
            mac.init(chaveHmac);
            return mac.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Falha ao gerar indice cego", e);
        }
    }

    private byte[] resolverChave(String chaveBase64) {
        if (StringUtils.isBlank(chaveBase64)) {
            log.warn("app.crypto.documento.key nao configurada. Gerando chave efemera; use apenas em dev, "
                    + "dados cifrados nao serao recuperaveis apos reiniciar a aplicacao.");
            byte[] efemera = new byte[TAMANHO_CHAVE];
            secureRandom.nextBytes(efemera);
            return efemera;
        }

        byte[] chave = Base64.getDecoder().decode(chaveBase64);
        if (chave.length != TAMANHO_CHAVE) {
            throw new IllegalStateException(
                    "app.crypto.documento.key deve ter 32 bytes (AES-256) em Base64. Bytes recebidos: " + chave.length);
        }
        return chave;
    }

    private byte[] derivarChaveHmac(byte[] chaveAes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(chaveAes);
            return digest.digest("indice-cego".getBytes(StandardCharsets.UTF_8));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Falha ao derivar chave do indice cego", e);
        }
    }
}
