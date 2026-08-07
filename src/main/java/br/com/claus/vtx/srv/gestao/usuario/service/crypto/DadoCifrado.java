package br.com.claus.vtx.srv.gestao.usuario.service.crypto;

public record DadoCifrado(byte[] conteudo, byte[] iv) {
}
