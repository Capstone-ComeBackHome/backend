package com.comebackhome.user.application;

import com.comebackhome.user.application.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.application.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.application.dto.UserMedicineUpdateRequestDto;

public interface UserCommandUseCase {

    void saveMyInfo(UserInfoSaveRequestDto userInfoSaveRequestDto, Long userId);

    void updateMyEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto, Long userId);

    void updateMyMedicineInfo(UserMedicineUpdateRequestDto userMedicineUpdateRequestDto, Long userId);
}
