package com.example.announcement_service.exception;

public class AnnouncementNotFoundException extends RuntimeException {
    public AnnouncementNotFoundException(Long announcementId) {
        super("Не найдено объявление с id: " + announcementId);
    }
}
