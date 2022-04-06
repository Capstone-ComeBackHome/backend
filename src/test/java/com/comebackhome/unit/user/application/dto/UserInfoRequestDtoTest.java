package com.comebackhome.unit.user.application.dto;

import com.comebackhome.user.application.dto.UserInfoRequestDto;
import com.comebackhome.user.domain.dto.UserInfoDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoRequestDtoTest {

    @Test
    void userInfoDto로_전환하기() throws Exception{
        //given
        UserInfoRequestDto userInfoRequestDto = givenUserInfoRequestDto();

        //when
        UserInfoDto result = userInfoRequestDto.toUserInfoDto();

        //then
        assertThat(result.getAge()).isEqualTo(userInfoRequestDto.getAge());
        assertThat(result.getSex()).isEqualTo(userInfoRequestDto.getSex());
        assertThat(result.getHeight()).isEqualTo(userInfoRequestDto.getHeight());
        assertThat(result.getWeight()).isEqualTo(userInfoRequestDto.getWeight());
        assertThat(result.getHistory()).isEqualTo(userInfoRequestDto.getHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(userInfoRequestDto.getFamilyHistory());
        assertThat(result.getDrugHistory()).isEqualTo(userInfoRequestDto.getDrugHistory());
        assertThat(result.getSocialHistory()).isEqualTo(userInfoRequestDto.getSocialHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(userInfoRequestDto.getTraumaHistory());
    }
}
