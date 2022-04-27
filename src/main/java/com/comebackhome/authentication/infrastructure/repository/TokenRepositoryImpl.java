package com.comebackhome.authentication.infrastructure.repository;

import com.comebackhome.authentication.domain.LogoutAccessToken;
import com.comebackhome.authentication.domain.LogoutRefreshToken;
import com.comebackhome.authentication.domain.RefreshToken;
import com.comebackhome.authentication.domain.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    private final LogoutRefreshTokenRedisRepository logoutRefreshTokenRedisRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Override
    public void saveLogoutAccessToken(LogoutAccessToken logoutAccessToken) {
        logoutAccessTokenRedisRepository.save(logoutAccessToken);
    }

    @Override
    public void saveLogoutRefreshToken(LogoutRefreshToken logoutRefreshToken) {
        logoutRefreshTokenRedisRepository.save(logoutRefreshToken);
    }

    @Override
    public void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRedisRepository.save(refreshToken);
    }

    @Override
    public boolean existsLogoutAccessTokenById(String token) {
        return logoutAccessTokenRedisRepository.existsById(token);
    }

    @Override
    public boolean existsLogoutRefreshTokenById(String token) {
        return logoutRefreshTokenRedisRepository.existsById(token);
    }

    @Override
    public boolean existsRefreshTokenById(String token) {
        return refreshTokenRedisRepository.existsById(token);
    }

    @Override
    public void deleteRefreshTokenById(String token) {
        refreshTokenRedisRepository.deleteById(token);
    }
}
