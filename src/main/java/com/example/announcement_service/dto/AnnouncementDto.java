package com.example.announcement_service.dto;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDto {
    private Long id;
    private Long userId;
    @NotNull(message = "Категория обязательна")
    private AnnouncementCategory category;
    @NotBlank(message = "Текст обязательно")
    private String text;
    private Double lat;
    private Double lng;
    private String address;
    @NotBlank(message = "Номер обязателен")
    private String phoneNumber;
    private LocalDateTime date;
    private List<String> images;
    private List<TagDto> tags;
}
