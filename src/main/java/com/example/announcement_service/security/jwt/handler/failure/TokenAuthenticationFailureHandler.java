package com.example.announcement_service.security.jwt.handler.failure;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.announcement_service.dto.response.SimpleErrorResponse;
import com.example.announcement_service.security.exception.AuthMethodNotSupportedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String message = "Неверные учетные данные";
        int status = HttpServletResponse.SC_BAD_REQUEST;

        if (exception instanceof BadCredentialsException
                || exception instanceof AuthMethodNotSupportedException) {
            message = exception.getMessage();
        } else if (exception.getCause() instanceof TokenExpiredException) {
            message = exception.getMessage();
            status = HttpServletResponse.SC_UNAUTHORIZED;
        }

        SimpleErrorResponse errorResponse = new SimpleErrorResponse(
                LocalDateTime.now(),
                status,
                message
        );
        response.setStatus(status);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
