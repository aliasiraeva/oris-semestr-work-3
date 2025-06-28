package com.example.announcement_service.dto.request;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import com.example.announcement_service.dto.TagDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AnnouncementFilterRequest {
    private List<AnnouncementCategory> categories;
    private Integer destination;
    private List<TagDto> tags;
}
