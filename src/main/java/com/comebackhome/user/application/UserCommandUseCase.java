package com.comebackhome.user.application;

import com.comebackhome.user.application.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.application.dto.UserInfoRequestDto;

public interface UserCommandUseCase {

    void updateMyInfo(UserInfoRequestDto userInfoRequestDto, Long userId);

    void updateMyEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto, Long userId);
}
