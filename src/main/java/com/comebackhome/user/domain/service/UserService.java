package com.comebackhome.user.domain.service;

import com.comebackhome.common.exception.user.UserNotFoundException;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.service.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
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
        User currentUser = getCurrentUserOrThrow(userId);
        currentUser.saveInfo(userInfoSaveRequestDto);
    }

    @Override
    public void updateMyEssentialInfo(UserEssentialUpdateRequestDto userEssentialUpdateRequestDto, Long userId) {
        User user = getCurrentUserOrThrow(userId);
        user.updateEssentialInfo(userEssentialUpdateRequestDto);
    }

    @Override
    public void updateMyMedicineInfo(UserMedicineUpdateRequestDto userMedicineUpdateRequestDto, Long userId) {
        User user = getCurrentUserOrThrow(userId);
        user.updateMedicineInfo(userMedicineUpdateRequestDto);
    }

    private User getCurrentUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
