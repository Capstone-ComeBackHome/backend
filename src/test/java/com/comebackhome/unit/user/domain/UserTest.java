package com.comebackhome.unit.user.domain;

import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.service.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
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
        UserInfoSaveRequestDto dto = givenUserInfoRequestDto();
        User user = givenUser();

        //when
        user.saveInfo(dto);

        //then
        assertThat(user.getAge()).isEqualTo(dto.getAge());
        assertThat(user.getSex()).isEqualTo(dto.getSex());
        assertThat(user.getHeight()).isEqualTo(dto.getHeight());
        assertThat(user.getWeight()).isEqualTo(dto.getWeight());
        assertThat(user.getHistory()).isEqualTo(dto.getHistory());
        assertThat(user.getFamilyHistory()).isEqualTo(dto.getFamilyHistory());
        assertThat(user.getDrugHistory()).isEqualTo(dto.getDrugHistory());
        assertThat(user.getSocialHistory()).isEqualTo(dto.getSocialHistory());
        assertThat(user.getTraumaHistory()).isEqualTo(dto.getTraumaHistory());
    }

    @Test
    void EssentialInfo_업데이트하기() throws Exception{
        //given
        UserEssentialUpdateRequestDto dto = givenUserEssentialUpdateRequestDto();
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
        UserMedicineUpdateRequestDto dto = givenUserMedicineUpdateRequestDto();
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
