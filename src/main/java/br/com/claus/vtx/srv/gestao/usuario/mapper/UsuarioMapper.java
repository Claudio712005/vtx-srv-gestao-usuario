package br.com.claus.vtx.srv.gestao.usuario.mapper;

import br.com.claus.vtx.srv.gestao.usuario.api.dto.request.IncluirUsuarioRequest;
import br.com.claus.vtx.srv.gestao.usuario.model.UsuarioEntity;
import br.com.claus.vtx.srv.gestao.usuario.service.crypto.CryptoService;
import br.com.claus.vtx.srv.gestao.usuario.service.crypto.DadoCifrado;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final CryptoService cryptoService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioEntity toEntity(IncluirUsuarioRequest request) {
        String documento = normalizarDocumento(request.getDocumento());
        DadoCifrado documentoCifrado = cryptoService.cifrar(documento);

        return UsuarioEntity.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .documento(documentoCifrado.conteudo())
                .documentoIv(documentoCifrado.iv())
                .documentoHash(cryptoService.indiceCego(documento))
                .dataNascimento(request.getDataNascimento())
                .build();
    }

    private String normalizarDocumento(String documento) {
        return documento.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }
}
