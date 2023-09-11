package by.it_academy.jd2.MJD29522.fitness.validator.api;

import by.it_academy.jd2.MJD29522.fitness.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= EmailValidator.class)
public @interface ValidEmail {
    String message() default "Incorrect email. Please check your email.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
