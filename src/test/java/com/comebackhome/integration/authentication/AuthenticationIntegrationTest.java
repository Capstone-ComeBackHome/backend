package com.comebackhome.integration.authentication;

import com.comebackhome.authentication.domain.RefreshToken;
import com.comebackhome.authentication.domain.repository.TokenRepository;
import com.comebackhome.authentication.domain.service.TokenProvider;
import com.comebackhome.authentication.infrastructure.JWTTokenProvider;
import com.comebackhome.authentication.infrastructure.repository.LogoutRefreshTokenRedisRepository;
import com.comebackhome.authentication.infrastructure.repository.RefreshTokenRedisRepository;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Iterator;

import static com.comebackhome.support.helper.UserGivenHelper.createAuthentication;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/";
    private final String REFRESH_HEADER = "refreshToken";
    private final String TOKEN_TYPE = "Bearer ";


    @Autowired UserRepository userRepository;
    @Autowired TokenRepository tokenRepository;
    @Autowired RefreshTokenRedisRepository refreshTokenRedisRepository;
    @Autowired LogoutRefreshTokenRedisRepository logoutRefreshTokenRedisRepository;

    @Test
    void 로그아웃() throws Exception{

        //given
        User user = userRepository.save(givenUser());
        Authentication authentication = createAuthentication(user);
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        //when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL+"logout")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + accessToken)
                .header(REFRESH_HEADER,TOKEN_TYPE + refreshToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void Refresh토큰이_아직_reissue_time이_남아있는_경우_토큰_재발급() throws Exception{
        //given
        User user = userRepository.save(givenUser());
        Authentication authentication = createAuthentication(user);
        String refreshToken = tokenProvider.createRefreshToken(authentication);
        tokenRepository.saveRefreshToken(RefreshToken.of(refreshToken, tokenProvider.getRemainingMilliSecondsFromToken(refreshToken)));

        //when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL+"reissue")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + refreshToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokenType",is(TOKEN_TYPE.trim())))
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.refreshToken").isNotEmpty())
        ;
        assertThat(tokenRepository.existsRefreshTokenById(refreshToken)).isTrue();
    }

    @Test
    void Refresh토큰이_reissue_time보다_적게_남은_경우_토큰_재발급() throws Exception{
        //given
        User user = userRepository.save(givenUser());
        Authentication authentication = createAuthentication(user);
        TokenProvider stubJWTTokenProvider = new JWTTokenProvider("test", 21600000L, 259200000L, 259200000L);
        String refreshToken = stubJWTTokenProvider.createRefreshToken(authentication);
        tokenRepository.saveRefreshToken(RefreshToken.of(refreshToken, stubJWTTokenProvider.getRemainingMilliSecondsFromToken(refreshToken)));

        //when then
        mockMvc.perform(MockMvcRequestBuilders.post(URL+"reissue")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + refreshToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokenType",is(TOKEN_TYPE.trim())))
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.refreshToken").isNotEmpty())
        ;

        assertThat(tokenRepository.existsRefreshTokenById(refreshToken)).isFalse();
        assertThat(logoutRefreshTokenRedisRepository.existsById(refreshToken)).isTrue();

        Iterator<RefreshToken> iterator = refreshTokenRedisRepository.findAll().iterator();
        int cnt=0;
        while (iterator.hasNext()){
            iterator.next();
            cnt+=1;
        }
        assertThat(cnt).isEqualTo(1);

    }


}
