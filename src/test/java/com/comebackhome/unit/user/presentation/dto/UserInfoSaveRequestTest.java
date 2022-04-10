package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.application.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.presentation.dto.UserInfoSaveRequest;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoSaveRequestTest {

    @Test
    void UserInfoRequestDto로_전환하기() throws Exception{
        //given
        UserInfoSaveRequest userInfoSaveRequest = givenUserInfoRequest();

        //when
        UserInfoSaveRequestDto result = userInfoSaveRequest.toUserInfoSaveRequestDto();

        //then
        assertThat(result.getAge()).isEqualTo(userInfoSaveRequest.getAge());
        assertThat(result.getSex()).isEqualTo(userInfoSaveRequest.getSex());
        assertThat(result.getHeight()).isEqualTo(userInfoSaveRequest.getHeight());
        assertThat(result.getWeight()).isEqualTo(userInfoSaveRequest.getWeight());
        assertThat(result.getHistory()).isEqualTo(userInfoSaveRequest.getHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(userInfoSaveRequest.getFamilyHistory());
        assertThat(result.getDrugHistory()).isEqualTo(userInfoSaveRequest.getDrugHistory());
        assertThat(result.getSocialHistory()).isEqualTo(userInfoSaveRequest.getSocialHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(userInfoSaveRequest.getTraumaHistory());
    }
}
