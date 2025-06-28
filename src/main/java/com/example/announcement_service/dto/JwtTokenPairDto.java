package com.example.announcement_service.dto;

import lombok.Builder;

@Builder
public record JwtTokenPairDto(
        String accessToken,

        String refreshToken
) {}
