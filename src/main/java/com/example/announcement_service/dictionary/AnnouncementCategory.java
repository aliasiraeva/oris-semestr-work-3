package com.example.announcement_service.dictionary;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AnnouncementCategory {
    LOSS,
    FIND,
    SALE;

    @JsonCreator
    public static AnnouncementCategory fromString(String value) {
        return value == null ? null : valueOf(value.toUpperCase());
    }
}
