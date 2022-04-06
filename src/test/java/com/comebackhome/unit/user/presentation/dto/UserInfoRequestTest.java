package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.application.dto.UserInfoRequestDto;
import com.comebackhome.user.presentation.dto.UserInfoRequest;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoRequestTest {

    @Test
    void UserInfoRequestDto로_전환하기() throws Exception{
        //given
        UserInfoRequest userInfoRequest = givenUserInfoRequest();

        //when
        UserInfoRequestDto result = userInfoRequest.toUserInfoRequestDto();

        //then
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
