package com.example.announcement_service.handler;

import com.example.announcement_service.dto.response.SimpleErrorResponse;
import com.example.announcement_service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleErrorResponse handleUserNotFound(UserNotFoundException e) {
        return new SimpleErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
    }

    @ExceptionHandler(AnnouncementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleErrorResponse handleAnnouncementNotFound(AnnouncementNotFoundException e) {
        return new SimpleErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleErrorResponse handleSubscriptionNotFound(SubscriptionNotFoundException e) {
        return new SimpleErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
    }

    @ExceptionHandler(AdvertisementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleErrorResponse handleAdvertisementNotFound(AdvertisementNotFoundException e) {
        return new SimpleErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
    }

    @ExceptionHandler(PhoneNumberAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SimpleErrorResponse handlePhoneNumberAlreadyTaken(PhoneNumberAlreadyTakenException e) {
        return new SimpleErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        );
    }



}
