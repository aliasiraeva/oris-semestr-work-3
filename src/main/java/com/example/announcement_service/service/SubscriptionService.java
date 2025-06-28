package com.example.announcement_service.service;

import com.example.announcement_service.dto.SubscriptionDto;
import com.example.announcement_service.entity.Tag;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionDto> findAllSubscriptionsByUserId(Long userId);

    SubscriptionDto createSubscription(SubscriptionDto subscriptionDto);

    void deleteSubscription(Long subscriptionId);

    void inactivateSubscriptions(Tag tag);
}
