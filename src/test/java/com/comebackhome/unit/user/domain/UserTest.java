package com.comebackhome.unit.user.domain;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.dto.UserInfoDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoDto;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void 소셜_로그인_시도_프로필이_업데이트된_경우() throws Exception{
        //given
        User user = givenUser();
        String name = "testName";
        String picture = "testPicture";

        //when
        user.updateBySocialProfile(name,picture);

        //then
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPicture()).isEqualTo(picture);
    }

    @Test
    void 개인_정보_업데이트() throws Exception{
        //given
        UserInfoDto userInfoDto = givenUserInfoDto();
        User user = givenUser();

        //when
        user.updateInfo(userInfoDto);

        //then
        assertThat(user.getAge()).isEqualTo(userInfoDto.getAge());
        assertThat(user.getSex()).isEqualTo(userInfoDto.getSex());
        assertThat(user.getHeight()).isEqualTo(userInfoDto.getHeight());
        assertThat(user.getWeight()).isEqualTo(userInfoDto.getWeight());
        assertThat(user.getHistory()).isEqualTo(userInfoDto.getHistory());
        assertThat(user.getFamilyHistory()).isEqualTo(userInfoDto.getFamilyHistory());
        assertThat(user.getDrugHistory()).isEqualTo(userInfoDto.getDrugHistory());
        assertThat(user.getSocialHistory()).isEqualTo(userInfoDto.getSocialHistory());
        assertThat(user.getTraumaHistory()).isEqualTo(userInfoDto.getTraumaHistory());
    }
}
