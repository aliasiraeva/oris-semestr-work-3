package com.example.announcement_service.exception;

public class PhoneNumberAlreadyTakenException extends RuntimeException {
    public PhoneNumberAlreadyTakenException(String phoneNumber) {
        super("Пользователь с таким номером телефона уже существует");
    }
}
