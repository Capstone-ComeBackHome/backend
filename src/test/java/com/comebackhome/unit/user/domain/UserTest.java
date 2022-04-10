package com.comebackhome.unit.user.domain;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.dto.UserEssentialUpdateDto;
import com.comebackhome.user.domain.dto.UserInfoDto;
import com.comebackhome.user.domain.dto.UserMedicineUpdateDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.UserGivenHelper.*;
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
        user.saveInfo(userInfoDto);

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

    @Test
    void EssentialInfo_업데이트하기() throws Exception{
        //given
        UserEssentialUpdateDto dto = givenUserEssentialUpdateDto();
        User user = givenUser();

        //when
        user.updateEssentialInfo(dto);

        //then
        assertThat(user.getAge()).isEqualTo(dto.getAge());
        assertThat(user.getSex()).isEqualTo(dto.getSex());
        assertThat(user.getHeight()).isEqualTo(dto.getHeight());
        assertThat(user.getWeight()).isEqualTo(dto.getWeight());
    }

    @Test
    void userMedicineInfo_업데이트하기() throws Exception{
        //given
        UserMedicineUpdateDto dto = givenUserMedicineUpdateDto();
        User user = givenUser();

        //when
        user.updateMedicineInfo(dto);

        //then
        assertThat(user.getHistory()).isEqualTo(dto.getHistory());
        assertThat(user.getSocialHistory()).isEqualTo(dto.getSocialHistory());
        assertThat(user.getDrugHistory()).isEqualTo(dto.getDrugHistory());
        assertThat(user.getTraumaHistory()).isEqualTo(dto.getTraumaHistory());
        assertThat(user.getFamilyHistory()).isEqualTo(dto.getFamilyHistory());
    }
}
