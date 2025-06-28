package com.example.announcement_service.repository.custom.impl;

import com.example.announcement_service.entity.Subscription;
import com.example.announcement_service.entity.Tag;
import com.example.announcement_service.repository.custom.CustomSubscriptionRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomSubscriptionRepositoryImpl implements CustomSubscriptionRepository {
    private final EntityManager entityManager;
    @Override
    public List<Subscription> findAllByTag(Tag tag) {

        return entityManager.createQuery("select s from Subscription s join s.tags t where t.tagName = :tagName",
                Subscription.class)
                .setParameter("tagName", tag.getTagName())
                .getResultList();
    }
}
