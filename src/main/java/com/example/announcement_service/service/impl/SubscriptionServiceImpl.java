package com.example.announcement_service.service.impl;

import com.example.announcement_service.dictionary.SubscriptionStatus;
import com.example.announcement_service.dto.SubscriptionDto;
import com.example.announcement_service.entity.Subscription;
import com.example.announcement_service.entity.Tag;
import com.example.announcement_service.mapper.SubscriptionMapper;
import com.example.announcement_service.repository.SubscriptionRepository;
import com.example.announcement_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<SubscriptionDto> findAllSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findAllByUserId(userId).stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }

    @Override
    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDto);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        if (subscriptionRepository.existsById(savedSubscription.getId())) {
            log.info("{} Подписка успешно сохранена", LocalDateTime.now());
            return subscriptionMapper.toDto(savedSubscription);
        } else {
            throw new RuntimeException("Ошибка сохранения подписки");
        }
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        if (subscriptionRepository.existsById(subscriptionId)) {
            subscriptionRepository.deleteById(subscriptionId);
        } else {
            throw new RuntimeException("Подписка не найдена");
        }
    }

    @Override
    public void inactivateSubscriptions(Tag tag) {
        subscriptionRepository.findAllByTag(tag).stream()
                .map(this::inactiveSubscription);
    }


    private Subscription inactiveSubscription(Subscription subscription) {
        subscription.setStatus(SubscriptionStatus.UNAVAILABLE);
        return subscription;
    }

}
