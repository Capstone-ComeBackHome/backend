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

import static com.comebackhome.support.helper.UserGivenHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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


    @Test
    void 개인_정보_심플_조회() throws Exception{
        // given
        User user = userRepository.save(givenUser());

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.picture", is(user.getPicture())))
                .andExpect(jsonPath("$.authProvider", is(user.getAuthProvider().name())))
        ;
    }

    @Test
    void 개인_정보_병력_조회() throws Exception{
        // given
        User user = userRepository.save(givenUserIncludeHistory());

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL+"/history")
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age", is(user.getAge())))
                .andExpect(jsonPath("$.sex", is(user.getSex().name())))
                .andExpect(jsonPath("$.height", is(user.getHeight())))
                .andExpect(jsonPath("$.weight", is(user.getWeight())))
                .andExpect(jsonPath("$.history", is(user.getHistory())))
                .andExpect(jsonPath("$.drugHistory", is(user.getDrugHistory())))
                .andExpect(jsonPath("$.socialHistory", is(user.getSocialHistory())))
                .andExpect(jsonPath("$.traumaHistory", is(user.getTraumaHistory())))
                .andExpect(jsonPath("$.familyHistory", is(user.getFamilyHistory())))

        ;
    }


}