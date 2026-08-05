package br.com.claus.vtx.srv.gestao.usuario.utils.validators;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.MinAge;
import jakarta.validation.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MinAgeValidatorTest {

    private static final int IDADE_MINIMA = 18;

    private MinAgeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new MinAgeValidator();
        validator.initialize(minAge(IDADE_MINIMA));
    }

    @Test
    void deveAceitarIdadeExatamenteNoLimite() {
        LocalDate nascimento = LocalDate.now().minusYears(IDADE_MINIMA);
        assertThat(validator.isValid(nascimento, null)).isTrue();
    }

    @Test
    void deveAceitarIdadeAcimaDoLimite() {
        LocalDate nascimento = LocalDate.now().minusYears(30);
        assertThat(validator.isValid(nascimento, null)).isTrue();
    }

    @Test
    void deveRejeitarIdadeAbaixoDoLimite() {
        LocalDate nascimento = LocalDate.now().minusYears(IDADE_MINIMA).plusDays(1);
        assertThat(validator.isValid(nascimento, null)).isFalse();
    }

    @Test
    void deveRejeitarRecemNascido() {
        assertThat(validator.isValid(LocalDate.now(), null)).isFalse();
    }

    @Test
    void deveAceitarValorNulo() {
        assertThat(validator.isValid(null, null)).isTrue();
    }

    private MinAge minAge(int valor) {
        return new MinAge() {
            @Override
            public String message() {
                return "";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return newPayloadArray();
            }

            @Override
            public int value() {
                return valor;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MinAge.class;
            }

            @SuppressWarnings("unchecked")
            private Class<? extends Payload>[] newPayloadArray() {
                return (Class<? extends Payload>[]) new Class<?>[0];
            }
        };
    }
}
