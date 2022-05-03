package com.comebackhome.authentication.domain.service;

import com.comebackhome.authentication.domain.service.dto.AuthResponseDto;

public interface AuthCommandUseCase {

    void logout(String accessToken, String refreshToken);

    AuthResponseDto reissue(String refreshToken);

}
