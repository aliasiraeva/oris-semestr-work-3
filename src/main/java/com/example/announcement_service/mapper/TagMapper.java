package com.example.announcement_service.mapper;

import com.example.announcement_service.dto.TagDto;
import com.example.announcement_service.entity.Tag;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TagMapper {
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto tagDto);
}
