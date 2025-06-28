package com.example.announcement_service.mapper;

import com.example.announcement_service.dto.AnnouncementDto;
import com.example.announcement_service.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AnnouncementMapper {
    AnnouncementDto toDto(Announcement announcement);
    @Mapping(target = "category", source = "category")
    Announcement toEntity(AnnouncementDto announcementDto);
}
