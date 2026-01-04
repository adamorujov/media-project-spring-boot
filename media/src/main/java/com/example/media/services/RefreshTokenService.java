package com.example.media.services;

import com.example.media.entities.RefreshToken;
import com.example.media.entities.User;
import com.example.media.repos.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${media.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(User user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if (token == null) {
            RefreshToken t = new RefreshToken();
            t.setUser(user);
        }

        String rawToken = UUID.randomUUID().toString();

        token.setToken(rawToken);
        token.setExpiryDate(LocalDateTime.now().plusSeconds(expireSeconds));

        refreshTokenRepository.save(token);
        return rawToken;
    }

    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    public RefreshToken getByUser(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }


}
