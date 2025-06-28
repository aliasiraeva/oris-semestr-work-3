package com.example.announcement_service.service;

import com.example.announcement_service.entity.RefreshToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JwtRefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    String generateRefreshToken(String phone, LocalDateTime expiredAt);
    Optional<String> validateRefreshToken(String token);
    void revokeRefreshToken(String token);
}
