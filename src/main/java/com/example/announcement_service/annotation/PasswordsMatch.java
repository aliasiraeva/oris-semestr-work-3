package com.example.announcement_service.annotation;

import com.example.announcement_service.validator.PasswordsMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordsMatch {
    String message() default "Пароли не совпадают";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}