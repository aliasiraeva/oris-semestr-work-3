package com.example.announcement_service.validator;

import com.example.announcement_service.annotation.PasswordsMatch;
import com.example.announcement_service.dto.request.UserRegistrationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserRegistrationRequest> {

    @Override
    public boolean isValid(UserRegistrationRequest request, ConstraintValidatorContext context) {
        if (request.password() == null || request.repeatPassword() == null) {
            return true;
        }
        return request.password().equals(request.repeatPassword());
    }
}