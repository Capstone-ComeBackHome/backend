package com.comebackhome.user.application;

import com.comebackhome.common.exception.user.UserNotFoundException;
import com.comebackhome.user.application.dto.UserInfoRequestDto;
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
    public void updateMyInfo(UserInfoRequestDto userInfoRequestDto, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        currentUser.updateInfo(userInfoRequestDto.toUserInfoDto());
    }
}
