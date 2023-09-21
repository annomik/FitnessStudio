package by.it_academy.jd2.MJD29522.fitness.validator;

import by.it_academy.jd2.MJD29522.fitness.validator.api.ValidString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringValidator implements ConstraintValidator<ValidString, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isBlank() || value.isEmpty()) {
            return false;
        }
        return true;
    }
}
