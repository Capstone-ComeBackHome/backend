package com.comebackhome.unit.user.application;

import com.comebackhome.common.exception.user.UserNotFoundException;
import com.comebackhome.user.application.UserService;
import com.comebackhome.user.application.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.application.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.comebackhome.support.helper.UserGivenHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks UserService userService;
    @Mock UserRepository userRepository;

    @Test
    void 내_정보_업데이트_하기() throws Exception{
        //given
        UserInfoSaveRequestDto userInfoSaveRequestDto = givenUserInfoRequestDto();
        User user = givenUser();
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        //when
        userService.saveMyInfo(userInfoSaveRequestDto,any());

        //then
        assertThat(user.getAge()).isEqualTo(userInfoSaveRequestDto.getAge());
        assertThat(user.getSex()).isEqualTo(userInfoSaveRequestDto.getSex());
        assertThat(user.getHeight()).isEqualTo(userInfoSaveRequestDto.getHeight());
        assertThat(user.getWeight()).isEqualTo(userInfoSaveRequestDto.getWeight());
        assertThat(user.getHistory()).isEqualTo(userInfoSaveRequestDto.getHistory());
        assertThat(user.getFamilyHistory()).isEqualTo(userInfoSaveRequestDto.getFamilyHistory());
        assertThat(user.getDrugHistory()).isEqualTo(userInfoSaveRequestDto.getDrugHistory());
        assertThat(user.getSocialHistory()).isEqualTo(userInfoSaveRequestDto.getSocialHistory());
        assertThat(user.getTraumaHistory()).isEqualTo(userInfoSaveRequestDto.getTraumaHistory());
    }

    @Test
    void 존재하지_않는_유저의_경우() throws Exception{
        //given
        UserInfoSaveRequestDto userInfoSaveRequestDto = givenUserInfoRequestDto();
        given(userRepository.findById(any())).willReturn(Optional.empty());

        //when
        assertThatThrownBy(() -> userService.saveMyInfo(userInfoSaveRequestDto,any()))
                .isInstanceOf(UserNotFoundException.class)
        ;
    }

    @Test
    void 필수_정보_수정하기() throws Exception{
        //given
        UserEssentialUpdateRequestDto dto = givenUserEssentialUpdateRequestDto();
        User user = givenUser();
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        //when
        userService.updateMyEssentialInfo(dto,any());

        //then
        assertThat(user.getAge()).isEqualTo(dto.getAge());
        assertThat(user.getSex()).isEqualTo(dto.getSex());
        assertThat(user.getHeight()).isEqualTo(dto.getHeight());
        assertThat(user.getWeight()).isEqualTo(dto.getWeight());
    }

    @Test
    void 필수_정보_수정_존재하지_않는_유저의_경우() throws Exception{
        //given
        UserEssentialUpdateRequestDto dto = givenUserEssentialUpdateRequestDto();
        given(userRepository.findById(any())).willReturn(Optional.empty());

        //when
        assertThatThrownBy(() -> userService.updateMyEssentialInfo(dto,any()))
                .isInstanceOf(UserNotFoundException.class)
        ;
    }

}
