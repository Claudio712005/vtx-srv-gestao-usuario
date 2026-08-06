package br.com.claus.vtx.srv.gestao.usuario.dto.request;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.CpfCnpj;
import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.MinAge;
import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.Sensitive;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "IncluirUsuarioRequest", description = "Request para inclusão de usuário")
public class IncluirUsuarioRequest {

    @NonNull
    @NotBlank(message = "O campo 'nome' é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O campo 'nome' deve conter apenas letras e espaços")
    @Schema(name = "nome", description = "Nome do usuário", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;

    @NonNull
    @Sensitive(digitosOcultos = 4)
    @NotBlank(message = "O campo 'email' é obrigatório")
    @Email(message = "O campo 'email' deve ser um endereço de e-mail válido")
    @Schema(name = "email", description = "E-mail do usuário", example = "joao@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NonNull
    @Sensitive(ocultar = true)
    @NotBlank(message = "O campo 'senha' é obrigatório")
    @Schema(name = "senha", description = "Senha do usuário", example = "#Senha123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Pattern(message = "A senha não deve conter espaços em branco", regexp = "^(?=\\S+$).*$")
    private String senha;

    @CpfCnpj(message = "O campo 'documento' deve ser um CPF ou CNPJ válido")
    @Sensitive(ocultar = true)
    @Schema(name = "documento", description = "Documento do usuário (CPF ou CNPJ)", example = "123.456.789-00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String documento;

    @NonNull
    @MinAge(18)
    @Schema(name = "dataNascimento", description = "Data de nascimento do usuário", example = "1990-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate dataNascimento;
}
