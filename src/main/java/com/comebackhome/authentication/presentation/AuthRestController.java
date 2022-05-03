package com.comebackhome.authentication.presentation;

import com.comebackhome.authentication.application.AuthFacade;
import com.comebackhome.authentication.domain.service.dto.AuthResponseDto;
import com.comebackhome.authentication.presentation.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthFacade authFacade;

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(value = "Authorization") String accessToken,
                                       @RequestHeader(value = "refreshToken") String refreshToken) {
        authFacade.logout(accessToken,refreshToken);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/reissue")
    public ResponseEntity<AuthResponse> reissueToken(@RequestHeader(value = "Authorization") String refreshToken){
        AuthResponseDto authResponseDto = authFacade.reissue(refreshToken);
        return ResponseEntity.ok(AuthResponse.from(authResponseDto));
    }

}
