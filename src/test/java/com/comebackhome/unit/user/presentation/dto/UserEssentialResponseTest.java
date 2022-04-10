package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.response.UserEssentialResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserIncludeInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class UserEssentialResponseTest {

    @Test
    void 정적_메서드_from으로_생성하기() throws Exception{
        //given
        User user = givenUserIncludeInfo();

        //when
        UserEssentialResponse result = UserEssentialResponse.from(user);

        //then
        assertThat(result.getAge()).isEqualTo(user.getAge());
        assertThat(result.getSex()).isEqualTo(user.getSex());
        assertThat(result.getHeight()).isEqualTo(user.getHeight());
        assertThat(result.getWeight()).isEqualTo(user.getWeight());
    }
}
