package br.com.claus.vtx.srv.gestao.usuario.utils.validators;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.CpfCnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Slf4j
public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    private static final Pattern CPF = Pattern.compile("[0-9]{11}");
    private static final Pattern CNPJ = Pattern.compile("[A-Z0-9]{12}[0-9]{2}");
    private static final int[] PESOS_PRIMEIRO_DIGITO = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }

        String documento = value.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        return switch (documento.length()) {
            case 11 -> isCpfValido(documento);
            case 14 -> isCnpjValido(documento);
            default -> false;
        };
    }

    private boolean isCpfValido(String cpf) {
        if (!CPF.matcher(cpf).matches() || possuiTodosCaracteresIguais(cpf)) {
            return false;
        }

        int primeiroDigito = calcularDigito(cpf, 9, 10);
        int segundoDigito = calcularDigito(cpf, 10, 11);

        return primeiroDigito == Character.getNumericValue(cpf.charAt(9))
                && segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    private boolean isCnpjValido(String cnpj) {
        if (!CNPJ.matcher(cnpj).matches() || possuiTodosCaracteresIguais(cnpj)) {
            return false;
        }

        int primeiroDigito = calcularDigitoCnpj(cnpj, PESOS_PRIMEIRO_DIGITO);
        int segundoDigito = calcularDigitoCnpj(cnpj, PESOS_SEGUNDO_DIGITO);

        return primeiroDigito == Character.getNumericValue(cnpj.charAt(12))
                && segundoDigito == Character.getNumericValue(cnpj.charAt(13));
    }

    private int calcularDigito(String cpf, int quantidade, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;
        for (int i = 0; i < quantidade; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int digito = 11 - (soma % 11);
        return digito >= 10 ? 0 : digito;
    }

    private int calcularDigitoCnpj(String cnpj, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private boolean possuiTodosCaracteresIguais(String documento) {
        return documento.chars().distinct().count() == 1;
    }
}
