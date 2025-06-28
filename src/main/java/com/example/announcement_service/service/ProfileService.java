package com.example.announcement_service.service;

import com.example.announcement_service.dto.AnnouncementDto;
import com.example.announcement_service.dto.request.UserUpdateProfileRequest;
import com.example.announcement_service.dto.response.UserProfileResponse;

import java.util.List;

public interface ProfileService {
    UserProfileResponse getUserProfile(Long userId);
    UserProfileResponse updateProfile(Long userId, UserUpdateProfileRequest request);
    List<AnnouncementDto> getUserAnnouncements(Long userId);
    List<AnnouncementDto> getAnnouncementsBySubscriptions(Long userId);
}
