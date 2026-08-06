package br.com.claus.vtx.srv.gestao.usuario.api.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Schema(description = "Controller responsável por gerenciar as operações relacionadas aos usuários.")
@Tag(name = "Usuário", description = "Operações relacionadas aos usuários.")
public class UsuarioController {

    @ApiResponses({
            @ApiResponse(description = "Usuário incluído com sucesso!", responseCode = "200"),
            @ApiResponse(description = "Erro ao incluir usuário.", responseCode = "400"),
            @ApiResponse(description = "Erro interno do servidor.", responseCode = "500"),
            @ApiResponse(description = "Erro de autenticação.", responseCode = "401"),
            @ApiResponse(description = "Erro de autorização.", responseCode = "403")
    })
    @PostMapping
    public ResponseEntity<String> incluirUsuario() {
        return ResponseEntity.ok("Usuário incluído com sucesso!");
    }
}
