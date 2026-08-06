package br.com.claus.vtx.srv.gestao.usuario.repository;

import br.com.claus.vtx.srv.gestao.usuario.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
