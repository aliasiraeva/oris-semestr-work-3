package com.example.announcement_service.exception;

public class AdvertisementNotFoundException extends RuntimeException {
    public AdvertisementNotFoundException(Long advertisementId) {
        super("Не найдено рекламы с id: " + advertisementId);
    }
}
