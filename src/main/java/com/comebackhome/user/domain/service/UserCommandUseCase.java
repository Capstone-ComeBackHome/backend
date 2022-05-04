package com.comebackhome.user.domain.service;

import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.service.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;

public interface UserCommandUseCase {

    void saveMyInfo(UserInfoSaveRequestDto userInfoSaveRequestDto, Long userId);

    void updateMyEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto, Long userId);

    void updateMyMedicineInfo(UserMedicineUpdateRequestDto userMedicineUpdateRequestDto, Long userId);
}
