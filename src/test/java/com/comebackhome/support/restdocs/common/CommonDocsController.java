package com.comebackhome.support.restdocs.common;

import com.comebackhome.authentication.presentation.dto.AuthResponse;
import com.comebackhome.common.CommonResponse;
import com.comebackhome.support.restdocs.SampleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * rest docs 용 컨트롤러
 * OAuth2 같은 외부 연동, 문서의 OverView에 표기할 내용들 문서화 하는 컨트로러
 */
@RestController
public class CommonDocsController {

    @GetMapping("/api/oauth2/authorization/{provider}")
    public ResponseEntity<CommonResponse<AuthResponse>> login() {
        return ResponseEntity.ok(CommonResponse.success(AuthResponse.of("accessToken","refreshToken")));
    }

    @GetMapping("/docs/error")
    public void docsError(@RequestBody @Validated SampleRequest sampleRequest){
    }

}
