package com.example.announcement_service.controller;

import com.example.announcement_service.dto.SubscriptionDto;
import com.example.announcement_service.security.details.UserDetailsImpl;
import com.example.announcement_service.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/list")
    public List<SubscriptionDto> getAllSubscriptionsByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return subscriptionService.findAllSubscriptionsByUserId(userDetails.getId());
    }


    @PostMapping
    public void postSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
        subscriptionService.createSubscription(subscriptionDto);
    }

    @DeleteMapping("/{id}")
    public void putSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }


}
