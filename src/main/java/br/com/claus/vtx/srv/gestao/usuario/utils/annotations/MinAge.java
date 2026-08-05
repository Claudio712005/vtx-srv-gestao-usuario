package br.com.claus.vtx.srv.gestao.usuario.utils.annotations;

import br.com.claus.vtx.srv.gestao.usuario.utils.validators.MinAgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinAgeValidator.class)
public @interface MinAge {

    String message() default "Usuário deve ter no mínimo {value} anos de idade.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}