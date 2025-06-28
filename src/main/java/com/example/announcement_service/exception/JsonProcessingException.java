package com.example.announcement_service.exception;

public class JsonProcessingException extends RuntimeException {
    public JsonProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}