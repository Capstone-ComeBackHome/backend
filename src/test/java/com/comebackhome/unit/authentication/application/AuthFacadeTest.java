package com.comebackhome.unit.authentication.application;

import com.comebackhome.authentication.application.AuthFacade;
import com.comebackhome.authentication.domain.service.AuthCommandUseCase;
import com.comebackhome.authentication.domain.service.dto.AuthResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthFacadeTest {

    @InjectMocks AuthFacade authFacade;
    @Mock AuthCommandUseCase authCommandUseCase;

    @Test
    void 토큰_재발급() {
        //given
        String refreshToken= "Bearer refresh";
        String accessToken = "Bearer access";
        AuthResponseDto authResponseDto = AuthResponseDto.of(accessToken, refreshToken);
        given(authCommandUseCase.reissue(any())).willReturn(authResponseDto);

        //when
        AuthResponseDto result = authFacade.reissue(refreshToken);

        //then
        assertThat(result.getAccessToken()).isEqualTo(authResponseDto.getAccessToken());
        assertThat(result.getRefreshToken()).isEqualTo(authResponseDto.getRefreshToken());
        assertThat(result.getTokenType()).isEqualTo(authResponseDto.getTokenType());
    }
}
