package br.com.claus.vtx.srv.gestao.usuario.model;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.Sensitive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "VTX_USR", schema = "GESTAO")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USR", nullable = false)
    private Long id;

    @Column(name = "NM_USR", nullable = false)
    private String nome;

    @Column(name = "EMAIL_USR", nullable = false, unique = true)
    private String email;

    @Sensitive
    @Column(name = "SENHA_USR", nullable = false)
    private String senha;

    @Sensitive(digitosOcultos = 4)
    @Column(name = "DOC_USR", nullable = false)
    private byte[] documento;

    @Column(name = "DOC_IV", nullable = false)
    private byte[] documentoIv;

    @Column(name = "DOC_HASH", nullable = false, unique = true)
    private byte[] documentoHash;

    @Column(name = "DT_NASC_USR", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "DT_CRIACAO", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;

}
