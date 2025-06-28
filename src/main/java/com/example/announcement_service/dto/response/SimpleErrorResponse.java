package com.example.announcement_service.dto.response;


import java.time.LocalDateTime;

public record SimpleErrorResponse(

        LocalDateTime timestamp,

        int status,

        String message
) {}