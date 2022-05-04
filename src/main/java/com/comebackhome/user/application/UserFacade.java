package com.comebackhome.user.application;

import com.comebackhome.user.domain.service.UserCommandUseCase;
import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.service.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserCommandUseCase userCommandUseCase;

    public void saveMyInfo(UserInfoSaveRequestDto userInfoSaveRequestDto, Long userId) {
        userCommandUseCase.saveMyInfo(userInfoSaveRequestDto,userId);
    }

    public void updateMyEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto, Long userId) {
        userCommandUseCase.updateMyEssentialInfo(userEssentialUpdateRequestDto,userId);
    }

    public void updateMyMedicineInfo(UserMedicineUpdateRequestDto userMedicineUpdateRequestDto, Long userId) {
        userCommandUseCase.updateMyMedicineInfo(userMedicineUpdateRequestDto,userId);
    }

}
