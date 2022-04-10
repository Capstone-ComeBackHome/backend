package com.comebackhome.user.presentation;


import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.user.application.UserCommandUseCase;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class UserRestController {

    private final UserCommandUseCase userCommandUseCase;

    @PatchMapping
    public ResponseEntity<Void> saveMyInfo(@Validated @RequestBody UserInfoSaveRequest userInfoSaveRequest,
                                           BindingResult errors,
                                           @LoginUser User user){

        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        userCommandUseCase.saveMyInfo(userInfoSaveRequest.toUserInfoSaveRequestDto(),user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserSimpleInfoResponse> getMySimpleInfo(@LoginUser User user){
        return ResponseEntity.ok(UserSimpleInfoResponse.from(user));
    }


    // info 초기 save하는거 하나로 만들도록 수정해야하고
    // 수정하는건 2개로 분리해야하고
    // 조회하는건 3개 만들어야한다.

    @GetMapping("/essential")
    public ResponseEntity<UserEssentialResponse> getMyEssentialInfo(@LoginUser User user){
        return ResponseEntity.ok(UserEssentialResponse.from(user));
    }

    @PatchMapping("/essential")
    public ResponseEntity<Void> updateMyEssentialInfo(@LoginUser User user,
                                                      @Validated @RequestBody UserEssentialUpdateRequest userEssentialUpdateRequest,
                                                      BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        userCommandUseCase.updateMyEssentialInfo(userEssentialUpdateRequest.toUserEssentialUpdateRequestDto(), user.getId());
        return ResponseEntity.ok().build();
    }

    // medicine 정보 조회 수정
//    @GetMapping("/medicine")
//    public ResponseEntity<UserMedicineResponse> getMyMedicineInfo(@LoginUser User user){
//        return ResponseEntity.ok(UserMedicineResponse.from(user));
//    }


    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getMyInfo(@LoginUser User user){
        return ResponseEntity.ok(UserInfoResponse.from(user));
    }

}
