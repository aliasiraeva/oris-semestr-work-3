package com.example.announcement_service.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(Long subscriptionId) {
        super("Не найдена подписка с id: " + subscriptionId);
    }
}
