package com.example.announcement_service.service.impl;

import com.example.announcement_service.dictionary.AnnouncementCategory;
import com.example.announcement_service.dto.AnnouncementDto;
import com.example.announcement_service.dto.request.UserUpdateProfileRequest;
import com.example.announcement_service.dto.response.UserProfileResponse;
import com.example.announcement_service.entity.Announcement;
import com.example.announcement_service.entity.Subscription;
import com.example.announcement_service.entity.Tag;
import com.example.announcement_service.entity.User;
import com.example.announcement_service.exception.UserNotFoundException;
import com.example.announcement_service.mapper.AnnouncementMapper;
import com.example.announcement_service.repository.AnnouncementRepository;
import com.example.announcement_service.repository.SubscriptionRepository;
import com.example.announcement_service.repository.UserRepository;
import com.example.announcement_service.service.AnnouncementService;
import com.example.announcement_service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final AnnouncementService announcementService;

    private final AnnouncementMapper announcementMapper;

    @Override
    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return UserProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public UserProfileResponse updateProfile(Long userId, UserUpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);

        return UserProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public List<AnnouncementDto> getUserAnnouncements(Long userId) {
        List<Announcement> announcements = announcementRepository.findAllByUserId(userId);
        if (announcements.isEmpty()) {
            throw new RuntimeException();
        } else {
            return announcements.stream().map(announcementMapper::toDto).toList();
        }
    }

    @Override
    public List<AnnouncementDto> getAnnouncementsBySubscriptions(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByUserId(userId);
        List<AnnouncementCategory> categories = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            categories.add(subscription.getAnnouncementCategory());
        }
        return announcementService.getAnnouncementsByCategories(categories);
    }

    private List<Announcement> getAnnouncementsByCategories(List<Subscription> subscriptions) {
        List<AnnouncementCategory> categories = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            categories.add(subscription.getAnnouncementCategory());
            tags.addAll(subscription.getTags());
        }
        List<Announcement> announcements = announcementService.getAnnouncementsByCategories(categories).stream().map(announcementMapper::toEntity).toList();
        return announcements.stream().filter(announcement -> announcement.getTags().stream().anyMatch(tags::contains)).toList();
    }


}
