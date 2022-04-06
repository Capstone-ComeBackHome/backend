package com.comebackhome.unit.user.presentation.dto;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.UserHistoryResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUserIncludeHistory;
import static org.assertj.core.api.Assertions.assertThat;

public class UserHistoryResponseTest {

    @Test
    void 정적_메서드_from_으로_생성하기() throws Exception{
        //given
        User user = givenUserIncludeHistory();

        //when
        UserHistoryResponse result = UserHistoryResponse.from(user);

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
