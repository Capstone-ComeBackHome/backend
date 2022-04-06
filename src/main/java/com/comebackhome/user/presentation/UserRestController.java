package com.comebackhome.user.presentation;


import com.comebackhome.common.LoginUser;
import com.comebackhome.common.exception.ValidatedException;
import com.comebackhome.user.application.UserCommandUseCase;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.presentation.dto.UserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class UserRestController {

    private final UserCommandUseCase userCommandUseCase;

    @PatchMapping
    public ResponseEntity<Void> updateMyInfo(@Validated @RequestBody UserInfoRequest userInfoRequest,
                                           BindingResult errors,
                                           @LoginUser User user){

        if (errors.hasErrors()){
            throw new ValidatedException(errors);
        }

        userCommandUseCase.updateMyInfo(userInfoRequest.toUserInfoRequestDto(),user.getId());
        return ResponseEntity.ok().build();
    }
}
