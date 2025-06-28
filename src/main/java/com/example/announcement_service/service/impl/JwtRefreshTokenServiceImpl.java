package com.example.announcement_service.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.announcement_service.entity.RefreshToken;
import com.example.announcement_service.repository.RefreshTokenRepository;
import com.example.announcement_service.service.JwtRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {

    private static final String SECRET_KEY = "${jwt.secret}";
    private static final String REFRESH_TOKEN_PREFIX = "refresh_token:";

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public String generateRefreshToken(String phone, LocalDateTime expiredAt) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(phone)
                .withExpiresAt(java.util.Date.from(expiredAt.toInstant(ZoneOffset.UTC)))
                .sign(algorithm);
    }

    @Override
    public Optional<String> validateRefreshToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return Optional.of(token);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void revokeRefreshToken(String token) {
    }
}