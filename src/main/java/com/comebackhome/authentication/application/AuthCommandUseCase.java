package com.comebackhome.authentication.application;

import com.comebackhome.authentication.application.dto.AuthResponseDto;

public interface AuthCommandUseCase {

    void logout(String accessToken, String refreshToken);

    AuthResponseDto reissue(String refreshToken);

}
