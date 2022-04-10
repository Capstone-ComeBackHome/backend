package com.comebackhome.unit.user.application.dto;

import com.comebackhome.user.application.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.dto.UserInfoDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoSaveRequestDtoTest {

    @Test
    void userInfoDto로_전환하기() throws Exception{
        //given
        UserInfoSaveRequestDto userInfoSaveRequestDto = givenUserInfoRequestDto();

        //when
        UserInfoDto result = userInfoSaveRequestDto.toUserInfoDto();

        //then
        assertThat(result.getAge()).isEqualTo(userInfoSaveRequestDto.getAge());
        assertThat(result.getSex()).isEqualTo(userInfoSaveRequestDto.getSex());
        assertThat(result.getHeight()).isEqualTo(userInfoSaveRequestDto.getHeight());
        assertThat(result.getWeight()).isEqualTo(userInfoSaveRequestDto.getWeight());
        assertThat(result.getHistory()).isEqualTo(userInfoSaveRequestDto.getHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(userInfoSaveRequestDto.getFamilyHistory());
        assertThat(result.getDrugHistory()).isEqualTo(userInfoSaveRequestDto.getDrugHistory());
        assertThat(result.getSocialHistory()).isEqualTo(userInfoSaveRequestDto.getSocialHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(userInfoSaveRequestDto.getTraumaHistory());
    }
}
