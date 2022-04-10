package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.response.UserInfoResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserIncludeInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoResponseTest {

    @Test
    void 정적_메서드_from_으로_생성하기() throws Exception{
        //given
        User user = givenUserIncludeInfo();

        //when
        UserInfoResponse result = UserInfoResponse.from(user);

        //then
        assertThat(result.getAge()).isEqualTo(user.getAge());
        assertThat(result.getSex()).isEqualTo(user.getSex());
        assertThat(result.getHeight()).isEqualTo(user.getHeight());
        assertThat(result.getWeight()).isEqualTo(user.getWeight());
        assertThat(result.getHistory()).isEqualTo(user.getHistory());
        assertThat(result.getFamilyHistory()).isEqualTo(user.getFamilyHistory());
        assertThat(result.getDrugHistory()).isEqualTo(user.getDrugHistory());
        assertThat(result.getSocialHistory()).isEqualTo(user.getSocialHistory());
        assertThat(result.getTraumaHistory()).isEqualTo(user.getTraumaHistory());
    }
}
