package com.example.announcement_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserLoginRequest(
        @NotBlank(message = "Номер телефона обязателен")
        String phoneNumber,

        @NotBlank(message = "Пароль обязателен")
        String password

) {}
