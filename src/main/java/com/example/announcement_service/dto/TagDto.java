package com.example.announcement_service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TagDto {
    private Long id;
    private String tagName;
}
