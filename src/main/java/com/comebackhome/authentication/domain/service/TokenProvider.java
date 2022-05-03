package com.comebackhome.authentication.domain.service;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String TOKEN_TYPE = "Bearer ";

    String createAccessToken(Authentication authentication);

    String createRefreshToken(Authentication authentication);

    String getUserEmailFromToken(String token);

    long getRemainingMilliSecondsFromToken(String token);

    boolean isMoreThanReissueTime(String token);

    boolean validateToken(String authToken);

    String removeType(String token);
}
