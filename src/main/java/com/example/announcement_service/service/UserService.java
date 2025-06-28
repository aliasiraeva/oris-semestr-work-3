package com.example.announcement_service.service;

import com.example.announcement_service.dto.JwtTokenPairDto;
import com.example.announcement_service.dto.request.UserRegistrationRequest;
import com.example.announcement_service.dto.response.UserExistsResponse;

public interface UserService {
    JwtTokenPairDto register(UserRegistrationRequest request);
    UserExistsResponse doesUserExistByPhoneNumber(String phoneNumber);

}
