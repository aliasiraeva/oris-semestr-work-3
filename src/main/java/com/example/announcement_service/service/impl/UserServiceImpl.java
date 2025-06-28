package com.example.announcement_service.service.impl;

import com.example.announcement_service.dictionary.UserRole;
import com.example.announcement_service.dto.JwtTokenPairDto;
import com.example.announcement_service.dto.request.UserRegistrationRequest;
import com.example.announcement_service.dto.response.UserExistsResponse;
import com.example.announcement_service.entity.User;
import com.example.announcement_service.exception.PhoneNumberAlreadyTakenException;
import com.example.announcement_service.repository.UserRepository;
import com.example.announcement_service.security.jwt.service.JwtService;
import com.example.announcement_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    @Override
    public JwtTokenPairDto register(UserRegistrationRequest request) {
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new PhoneNumberAlreadyTakenException(request.phoneNumber());
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phoneNumber(request.phoneNumber())
                .password(encoder.encode(request.password()))
                .role(UserRole.USER)
                .build();
        userRepository.save(user);

        return jwtService.getTokenPair(request.phoneNumber());
    }

    @Override
    public UserExistsResponse doesUserExistByPhoneNumber(String phoneNumber) {
        boolean exists = userRepository.existsByPhoneNumber(phoneNumber);
        return new UserExistsResponse(exists);
    }

}
