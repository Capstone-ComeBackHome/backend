package com.comebackhome.integration.user;

import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import com.comebackhome.user.presentation.dto.UserInfoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/users";
    private final String TOKEN_TYPE = "Bearer ";

    @Autowired UserRepository userRepository;

    @Test
    void 개인_정보_업데이트_하기() throws Exception{
        // given
        User user = userRepository.save(givenUser());
        UserInfoRequest userInfoRequest = givenUserInfoRequest();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL)
                .content(createJson(userInfoRequest))
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;

        flushAndClear();

        User result = userRepository.findById(user.getId()).get();
        assertThat(result.getAge()).isEqualTo(userInfoRequest.getAge());
        assertThat(result.getSex()).isEqualTo(userInfoRequest.getSex());
        assertThat(result.getHeight()).isEqualTo(userInfoRequest.getHeight());
        assertThat(result.getWeight()).isEqualTo(userInfoRequest.getWeight());
        assertThat(result.getHistory()).isEqualTo(userInfoRequest.getHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(userInfoRequest.getFamilyHistory());
        assertThat(result.getDrugHistory()).isEqualTo(userInfoRequest.getDrugHistory());
        assertThat(result.getSocialHistory()).isEqualTo(userInfoRequest.getSocialHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(userInfoRequest.getTraumaHistory());
    }
}
