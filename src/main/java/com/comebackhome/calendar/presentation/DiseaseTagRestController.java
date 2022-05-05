package com.comebackhome.calendar.presentation;

import com.comebackhome.calendar.application.DiseaseTagFacade;
import com.comebackhome.calendar.presentation.dto.response.DefaultTypeDiseaseTagListResponse;
import com.comebackhome.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diseaseTags")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class DiseaseTagRestController {

    private final DiseaseTagFacade diseaseTagFacade;

    @GetMapping
    public ResponseEntity<CommonResponse<DefaultTypeDiseaseTagListResponse>> getDiseaseTagExceptCustomType() {
        DefaultTypeDiseaseTagListResponse defaultTypeDiseaseTagListResponse =
                DefaultTypeDiseaseTagListResponse.from(diseaseTagFacade.getDiseaseTagExceptCustomType());
        return ResponseEntity.ok(CommonResponse.success(defaultTypeDiseaseTagListResponse));
    }
}
