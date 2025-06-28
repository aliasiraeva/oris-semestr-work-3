package com.example.announcement_service.dto;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import com.example.announcement_service.dictionary.SubscriptionStatus;
import com.example.announcement_service.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;
    private Long userId;
    private String title;
    private AnnouncementCategory announcementCategory;
    private List<Tag> tags;
    private SubscriptionStatus status;
}
