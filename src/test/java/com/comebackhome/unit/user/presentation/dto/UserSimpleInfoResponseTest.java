package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.response.UserSimpleInfoResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserIncludeInfo;
import static org.assertj.core.api.Assertions.assertThat;

public class UserSimpleInfoResponseTest {

    @Test
    void 정적_메서드_from_으로_생성하기() throws Exception{
        //given
        User user = givenUserIncludeInfo();

        //when
        UserSimpleInfoResponse result = UserSimpleInfoResponse.from(user);

        //then
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getPicture()).isEqualTo(user.getPicture());
        assertThat(result.getAuthProvider()).isEqualTo(user.getAuthProvider());
    }
}
