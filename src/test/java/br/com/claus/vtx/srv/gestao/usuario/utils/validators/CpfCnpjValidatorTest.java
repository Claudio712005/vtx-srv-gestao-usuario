package br.com.claus.vtx.srv.gestao.usuario.utils.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CpfCnpjValidatorTest {

    private CpfCnpjValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CpfCnpjValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11144477735", "111.444.777-35"})
    void deveAceitarCpfValido(String cpf) {
        assertThat(validator.isValid(cpf, null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11144477730", "11111111111", "123456789"})
    void deveRejeitarCpfInvalido(String cpf) {
        assertThat(validator.isValid(cpf, null)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11222333000181", "11.222.333/0001-81"})
    void deveAceitarCnpjNumericoValido(String cnpj) {
        assertThat(validator.isValid(cnpj, null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"12ABC34501DE35", "12abc34501de35", "12.ABC.345/01DE-35"})
    void deveAceitarCnpjAlfanumericoValido(String cnpj) {
        assertThat(validator.isValid(cnpj, null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"11222333000180", "12ABC34501DE34", "AAAAAAAAAAAA00", "1122233300018"})
    void deveRejeitarCnpjInvalido(String cnpj) {
        assertThat(validator.isValid(cnpj, null)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "!@#$%"})
    void deveRejeitarValorVazioOuSemDigitos(String valor) {
        assertThat(validator.isValid(valor, null)).isFalse();
    }

    @Test
    void deveRejeitarTamanhoInvalido() {
        assertThat(validator.isValid("123", null)).isFalse();
    }
}
