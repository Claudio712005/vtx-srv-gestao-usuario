package br.com.claus.vtx.srv.gestao.usuario.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CpfCnpj {
    String mensagem() default "Documento inválido. Informe um CPF ou CNPJ válido.";
}
