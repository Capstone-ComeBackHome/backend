package com.comebackhome.user.presentation;


import com.comebackhome.common.CommonResponse;
import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.user.application.UserFacade;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.request.UserEssentialUpdateRequest;
import com.comebackhome.user.presentation.dto.request.UserInfoSaveRequest;
import com.comebackhome.user.presentation.dto.request.UserMedicineUpdateRequest;
import com.comebackhome.user.presentation.dto.response.UserEssentialResponse;
import com.comebackhome.user.presentation.dto.response.UserInfoResponse;
import com.comebackhome.user.presentation.dto.response.UserMedicineResponse;
import com.comebackhome.user.presentation.dto.response.UserSimpleInfoResponse;
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

    private final UserFacade userFacade;

    @PatchMapping
    public ResponseEntity<CommonResponse> saveMyInfo(@Validated @RequestBody UserInfoSaveRequest userInfoSaveRequest,
                                           BindingResult errors,
                                           @LoginUser User user){

        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        userFacade.saveMyInfo(userInfoSaveRequest.toUserInfoSaveRequestDto(),user.getId());
        return ResponseEntity.ok(CommonResponse.success());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<UserSimpleInfoResponse>> getMySimpleInfo(@LoginUser User user){
        return ResponseEntity.ok(CommonResponse.success(UserSimpleInfoResponse.from(user)));
    }

    @GetMapping("/essential")
    public ResponseEntity<CommonResponse<UserEssentialResponse>> getMyEssentialInfo(@LoginUser User user){
        return ResponseEntity.ok(CommonResponse.success(UserEssentialResponse.from(user)));
    }

    @PatchMapping("/essential")
    public ResponseEntity<CommonResponse> updateMyEssentialInfo(@LoginUser User user,
                                                      @Validated @RequestBody UserEssentialUpdateRequest userEssentialUpdateRequest,
                                                      BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        userFacade.updateMyEssentialInfo(userEssentialUpdateRequest.toUserEssentialUpdateRequestDto(), user.getId());
        return ResponseEntity.ok(CommonResponse.success());
    }

    @GetMapping("/medicine")
    public ResponseEntity<CommonResponse<UserMedicineResponse>> getMyMedicineInfo(@LoginUser User user){
        return ResponseEntity.ok(CommonResponse.success(UserMedicineResponse.from(user)));
    }

    @PatchMapping("/medicine")
    public ResponseEntity<CommonResponse> updateMyMedicineInfo(@LoginUser User user,
                        @Validated @RequestBody UserMedicineUpdateRequest userMedicineUpdateRequest,
                        BindingResult errors){

        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        userFacade.updateMyMedicineInfo(userMedicineUpdateRequest.toUserMedicineUpdateRequestDto(),user.getId());

        return ResponseEntity.ok(CommonResponse.success());
    }


    @GetMapping("/info")
    public ResponseEntity<CommonResponse<UserInfoResponse>> getMyInfo(@LoginUser User user){
        return ResponseEntity.ok(CommonResponse.success(UserInfoResponse.from(user)));
    }

}
