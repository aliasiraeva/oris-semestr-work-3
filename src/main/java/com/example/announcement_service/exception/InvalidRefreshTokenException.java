package com.example.announcement_service.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidRefreshTokenException extends AuthenticationException {
    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}