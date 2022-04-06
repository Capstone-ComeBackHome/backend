package com.comebackhome.user.application;

import com.comebackhome.user.application.dto.UserInfoRequestDto;

public interface UserCommandUseCase {

    void updateMyInfo(UserInfoRequestDto userInfoRequestDto, Long userId);
}
