package br.com.claus.vtx.srv.gestao.usuario.utils.validators;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.CpfCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    @Override
    public void initialize(CpfCnpj constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(StringUtils.isNotBlank(value)){
            String documento = value.replaceAll("[^a-zA-Z0-9]", "");

            if(documento.length() == 11){
                return isCpfValido(documento);
            } else if(documento.length() == 14){
                return isCnpjAlfaValido(documento) || isCnpjNumericoValido(documento);
            }
        }

        return false;
    }

    private boolean isCpfValido(String cpf) {
        char dig10, dig11;
        int sm, i, r, num, peso;

        try{
            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isCnpjAlfaValido(String cnpj) {
        // Implementação da validação de CNPJ
        return true; // Retorne true se o CNPJ for válido, caso contrário, false
    }

    private boolean isCnpjNumericoValido(String cnpj) {
        // Implementação da validação de CNPJ
        return true; // Retorne true se o CNPJ for válido, caso contrário, false
    }
}
