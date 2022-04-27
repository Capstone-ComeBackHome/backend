package com.comebackhome.unit.authentication.domain;

import com.comebackhome.authentication.domain.RefreshToken;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenTest {

    @Test
    void LogoutRefreshToken_정적_메서드_생성() throws Exception{
        //given
        String refreshToken = "refreshToken";
        long expiration = 86400000;

        //when
        RefreshToken result = RefreshToken.of(refreshToken, expiration);

        //then
        assertThat(result.getId()).isEqualTo(refreshToken);
        assertThat(result.getExpiration()).isEqualTo(expiration);
    }
}
