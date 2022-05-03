package com.comebackhome.config.security.handler;

import com.comebackhome.authentication.domain.RefreshToken;
import com.comebackhome.authentication.domain.TokenRepository;
import com.comebackhome.authentication.domain.service.TokenProvider;
import com.comebackhome.authentication.presentation.dto.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;
    private final TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String refreshToken = tokenProvider.createRefreshToken(authentication);
        String accessToken = tokenProvider.createAccessToken(authentication);
        AuthResponse authResponse = AuthResponse.of(accessToken,refreshToken);
        tokenRepository
                .saveRefreshToken(RefreshToken.of(refreshToken, tokenProvider.getRemainingMilliSecondsFromToken(refreshToken)));

        objectMapper.writeValue(response.getWriter(), authResponse);
    }
}
