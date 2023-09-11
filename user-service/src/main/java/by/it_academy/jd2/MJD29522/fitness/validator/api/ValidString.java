package by.it_academy.jd2.MJD29522.fitness.validator.api;

import by.it_academy.jd2.MJD29522.fitness.validator.StringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= StringValidator.class)
public @interface ValidString {
    String message() default "Please, fill in the field";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
