package com.comebackhome.user.application;

import com.comebackhome.common.exception.user.UserNotFoundException;
import com.comebackhome.user.application.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.application.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserCommandUseCase{

    private final UserRepository userRepository;

    @Override
    public void saveMyInfo(UserInfoSaveRequestDto userInfoSaveRequestDto, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        currentUser.saveInfo(userInfoSaveRequestDto.toUserInfoDto());
    }

    @Override
    public void updateMyEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto, Long userId) {
        User user = userRepository.findById(userId) // todo 함수로 빼기
                .orElseThrow(() -> new UserNotFoundException());
        user.updateEssentialInfo(userEssentialUpdateRequestDto.toUserEssentialUpdateDto());
    }
}
