package br.com.claus.vtx.srv.gestao.usuario.mapper;

import br.com.claus.vtx.srv.gestao.usuario.api.dto.request.IncluirUsuarioRequest;
import br.com.claus.vtx.srv.gestao.usuario.model.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity toEntity(IncluirUsuarioRequest request) {
        return UsuarioEntity.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(request.getSenha())
                .dataNascimento(request.getDataNascimento())
                .build();
    }
}
