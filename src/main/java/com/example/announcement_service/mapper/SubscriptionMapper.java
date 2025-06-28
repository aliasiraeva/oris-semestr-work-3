package com.example.announcement_service.mapper;

import com.example.announcement_service.dto.SubscriptionDto;
import com.example.announcement_service.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SubscriptionMapper {
    SubscriptionDto toDto(Subscription subscription);
    @Mapping(target = "announcementCategory", source = "announcementCategory")
    Subscription toEntity(SubscriptionDto subscriptionDto);
}
