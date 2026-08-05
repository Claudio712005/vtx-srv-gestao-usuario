package br.com.claus.vtx.srv.gestao.usuario.utils.validators;

import br.com.claus.vtx.srv.gestao.usuario.utils.annotations.MinAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {

    private int minAge;

    @Override
    public void initialize(MinAge annotation) {
        this.minAge = annotation.value();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {

        if (birthDate == null) {
            return true;
        }

        return !birthDate.isAfter(LocalDate.now().minusYears(minAge));
    }
}