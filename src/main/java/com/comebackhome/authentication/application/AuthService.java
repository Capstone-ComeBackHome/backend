package com.comebackhome.authentication.application;

import com.comebackhome.authentication.application.dto.AuthResponseDto;
import com.comebackhome.authentication.domain.LogoutAccessToken;
import com.comebackhome.authentication.domain.LogoutRefreshToken;
import com.comebackhome.authentication.domain.RefreshToken;
import com.comebackhome.authentication.domain.TokenRepository;
import com.comebackhome.common.exception.security.NotExistsRefreshTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements AuthCommandUseCase {

    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;

    @Override
    public void logout(String accessToken, String refreshToken) {
        saveLogoutAccessToken(accessToken);
        saveLogoutRefreshToken(refreshToken);
    }

    private void saveLogoutAccessToken(String accessToken) {
        String removedTypeAccessToken = getRemovedBearerType(accessToken);
        LogoutAccessToken logoutAccessToken = LogoutAccessToken.of(removedTypeAccessToken,
                getRemainingMilliSecondsFromToken(removedTypeAccessToken));
        tokenRepository.saveLogoutAccessToken(logoutAccessToken);
    }

    private void saveLogoutRefreshToken(String refreshToken) {
        String removedTypeRefreshToken = getRemovedBearerType(refreshToken);
        LogoutRefreshToken logoutRefreshToken = LogoutRefreshToken.of(removedTypeRefreshToken,
                getRemainingMilliSecondsFromToken(removedTypeRefreshToken));
        tokenRepository.saveLogoutRefreshToken(logoutRefreshToken);
    }

    private String getRemovedBearerType(String token){
        return token.substring(7);
    }

    @Override
    public AuthResponseDto reissue(String refreshToken) {
        refreshToken = tokenProvider.removeType(refreshToken);
        isInRedisOrThrow(refreshToken);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String newAccessToken = tokenProvider.createAccessToken(authentication);
        if (tokenProvider.isMoreThanReissueTime(refreshToken))
            return AuthResponseDto.of(newAccessToken, refreshToken);

        deleteOriginRefreshToken(refreshToken);
        String newRefreshToken = createNewRefreshToken(authentication);
        return AuthResponseDto.of(newAccessToken, newRefreshToken);
    }

    private void isInRedisOrThrow(String refreshToken) {
        if (!tokenRepository.existsRefreshTokenById(refreshToken)){
            throw new NotExistsRefreshTokenException();
        }
    }

    private void deleteOriginRefreshToken(String refreshToken) {
        tokenRepository.deleteRefreshTokenById(refreshToken);
        tokenRepository.saveLogoutRefreshToken(
                LogoutRefreshToken.of(refreshToken,getRemainingMilliSecondsFromToken(refreshToken)));
    }

    private String createNewRefreshToken(Authentication authentication) {
        String newRefreshToken = tokenProvider.createRefreshToken(authentication);
        tokenRepository.saveRefreshToken(
                RefreshToken.of(newRefreshToken,getRemainingMilliSecondsFromToken(newRefreshToken)));
        return newRefreshToken;
    }

    private long getRemainingMilliSecondsFromToken(String token) {
        return tokenProvider.getRemainingMilliSecondsFromToken(token);
    }
}
