package com.comebackhome.unit.user.application.dto;

import com.comebackhome.user.application.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.dto.UserEssentialUpdateDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserEssentialUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

public class UserEssentialUpdateRequestDtoTest {

    @Test
    void UserEssentialUpdateDto로_전환하기() throws Exception{
        //given
        UserEssentialUpdateRequestDto dto = givenUserEssentialUpdateRequestDto();

        //when
        UserEssentialUpdateDto result = dto.toUserEssentialUpdateDto();

        //then
        assertThat(result.getAge()).isEqualTo(dto.getAge());
        assertThat(result.getSex()).isEqualTo(dto.getSex());
        assertThat(result.getHeight()).isEqualTo(dto.getHeight());
        assertThat(result.getWeight()).isEqualTo(dto.getWeight());
    }
}
