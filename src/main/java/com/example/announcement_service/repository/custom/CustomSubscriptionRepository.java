package com.example.announcement_service.repository.custom;

import com.example.announcement_service.entity.Subscription;
import com.example.announcement_service.entity.Tag;

import java.util.List;

public interface CustomSubscriptionRepository {
    List<Subscription> findAllByTag(Tag tag);
}
