package com.example.announcement_service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("Не найден пользователь с id: " + userId);
    }

}
