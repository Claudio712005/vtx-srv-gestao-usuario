package br.com.claus.vtx.srv.gestao.usuario.service;

import br.com.claus.vtx.srv.gestao.usuario.api.dto.request.IncluirUsuarioRequest;
import br.com.claus.vtx.srv.gestao.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public void salvarUsuario(IncluirUsuarioRequest req){

    }
}
