package com.comebackhome.unit.authentication.infrastructure;


import com.comebackhome.authentication.infrastructure.JWTTokenProvider;
import com.comebackhome.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.createAuthentication;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class JWTTokenProviderTest {

    JWTTokenProvider JWTTokenProvider;

    @BeforeEach
    void setup(){
        JWTTokenProvider = new JWTTokenProvider("secret",21600000L,
                259200000L,604800000L);
    }

    @Test
    void accessToken_만들기() throws Exception{
        //given
        User user = givenUser();

        //when
        String accessToken = getAccessToken(user);

        //then
        String userEmailFromToken = JWTTokenProvider.getUserEmailFromToken(accessToken);
        assertThat(userEmailFromToken).isEqualTo(user.getEmail());
    }

    @Test
    void refreshToken_만들기() throws Exception{
        //given
        User user = givenUser();

        //when
        String refreshToken = JWTTokenProvider.createRefreshToken(createAuthentication(user));

        //then
        String userEmailFromToken = JWTTokenProvider.getUserEmailFromToken(refreshToken);
        assertThat(userEmailFromToken).isEqualTo(user.getEmail());
    }

    @Test
    void 토큰에서_이메일_추출하기() throws Exception{
        //given
        User user = givenUser();
        String accessToken = getAccessToken(user);

        //when
        String userEmailFromToken = JWTTokenProvider.getUserEmailFromToken(accessToken);

        //then
        assertThat(userEmailFromToken).isEqualTo(user.getEmail());
    }

    @Test
    void 토큰_남은_시간_추출하기() throws Exception{
        //given
        String accessToken = getAccessToken(givenUser());

        //when
        long remainingMilliSecondsFromToken = JWTTokenProvider.getRemainingMilliSecondsFromToken(accessToken);

        //then
        assertThat(remainingMilliSecondsFromToken).isLessThan(21600000L);
    }

    @Test
    void 토큰_유효성_체크_성공() throws Exception{
        //given
        String accessToken = getAccessToken(givenUser());

        //when
        boolean result = JWTTokenProvider.validateToken(accessToken);

        // then
        assertThat(result).isTrue();
    }



    @Test
    void secret이_다른_토큰_유효성_체크() throws Exception{
        //given
        String accessToken = getAccessToken(givenUser());
        JWTTokenProvider anotherJWTTokenProvider = new JWTTokenProvider("new_secret", 10000L, 10000L,10000L);

        //when
        boolean result = anotherJWTTokenProvider.validateToken(accessToken);

        // then
        assertThat(result).isFalse();
    }

    private String getAccessToken(User user) {
        return JWTTokenProvider.createAccessToken(createAuthentication(user));
    }

    @Test
    void 구조적_문제가_있는_토큰_유효성_체크() throws Exception{
        //given
        String accessToken = "dummy";

        //when
        boolean result = JWTTokenProvider.validateToken(accessToken);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 만료된_토큰_유효성_체크() throws Exception{
        //given
        JWTTokenProvider = new JWTTokenProvider("secret", 0L, 0L,0L);
        String accessToken = JWTTokenProvider.createAccessToken(createAuthentication(givenUser()));

        //when
        boolean result = JWTTokenProvider.validateToken(accessToken);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void refreshToken_유효시간이_재발급_기간보다_적게_남은_경우() {
        //given
        JWTTokenProvider = new JWTTokenProvider("secret", 1000L, 1000L,1000L);
        String refreshToken = JWTTokenProvider.createRefreshToken(createAuthentication(givenUser()));

        //when
        boolean result = JWTTokenProvider.isMoreThanReissueTime(refreshToken);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void refreshToken_유효시간이_재발급_기간보다_많이_남은_경우() {
        //given
        JWTTokenProvider = new JWTTokenProvider("secret", 1000L, 100000L,1000L);
        String refreshToken = JWTTokenProvider.createRefreshToken(createAuthentication(givenUser()));

        //when
        boolean result = JWTTokenProvider.isMoreThanReissueTime(refreshToken);

        //then
        assertThat(result).isTrue();
    }


}
