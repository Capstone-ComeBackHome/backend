package com.comebackhome.authentication.application;

import com.comebackhome.authentication.domain.service.AuthCommandUseCase;
import com.comebackhome.authentication.domain.service.dto.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacade {

    private final AuthCommandUseCase authCommandUseCase;

    public void logout(String accessToken, String refreshToken) {
        authCommandUseCase.logout(accessToken,refreshToken);
    }

    public AuthResponseDto reissue(String refreshToken) {
        return authCommandUseCase.reissue(refreshToken);
    }
}
