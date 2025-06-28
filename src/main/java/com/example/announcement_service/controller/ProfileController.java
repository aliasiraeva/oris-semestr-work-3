package com.example.announcement_service.controller;

import com.example.announcement_service.dto.request.UserUpdateProfileRequest;
import com.example.announcement_service.dto.response.UserProfileResponse;
import com.example.announcement_service.security.details.UserDetailsImpl;
import com.example.announcement_service.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping()
    public UserProfileResponse getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.getUserProfile(userDetails.getId());
    }

    @PatchMapping("/edit")
    public UserProfileResponse updateProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UserUpdateProfileRequest request) {
        return profileService.updateProfile(userDetails.getId(), request);
    }
}
