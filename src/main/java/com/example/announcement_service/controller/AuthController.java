package com.example.announcement_service.controller;

import com.example.announcement_service.dto.JwtTokenPairDto;
import com.example.announcement_service.dto.request.UserLoginRequest;
import com.example.announcement_service.dto.request.UserRegistrationRequest;
import com.example.announcement_service.dto.response.UserExistsResponse;
import com.example.announcement_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtTokenPairDto register(@RequestBody @Valid UserRegistrationRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public JwtTokenPairDto login(@RequestBody @Valid UserLoginRequest request) {
        return null;
    }

    @PostMapping("/refresh")
    public JwtTokenPairDto refresh() {
        return null;
    }

    @GetMapping("/check")
    public UserExistsResponse doesUserExistByPhoneNumber(
            @RequestParam @Pattern(regexp = "^7\\d{10}$",
                    message = "Номер телефона должен быть в формате 7XXXXXXXXXX") String phone) {
        return userService.doesUserExistByPhoneNumber(phone);
    }
}