package com.example.announcement_service.mapper;

import com.example.announcement_service.dto.AdvertisementDto;
import com.example.announcement_service.entity.Advertisement;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AdvertisementMapper {
    AdvertisementDto toDto(Advertisement advertisement);
    Advertisement toEntity(AdvertisementDto advertisementDto);
}
