package br.com.claus.vtx.srv.gestao.usuario.utils.annotations;

import br.com.claus.vtx.srv.gestao.usuario.utils.validators.CpfCnpjValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfCnpjValidator.class)
public @interface CpfCnpj {

    String message() default "Documento inválido. Informe um CPF ou CNPJ válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
