package com.example.announcement_service.repository;

import com.example.announcement_service.entity.Subscription;
import com.example.announcement_service.repository.custom.CustomSubscriptionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, CustomSubscriptionRepository {
    List<Subscription> findAllByUserId(Long userId);
}
