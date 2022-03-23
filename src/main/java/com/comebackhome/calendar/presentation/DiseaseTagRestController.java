package com.comebackhome.calendar.presentation;

import com.comebackhome.calendar.application.DiseaseTagQueryUseCase;
import com.comebackhome.calendar.presentation.dto.DefaultTypeDiseaseTagListResponse;
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

    private final DiseaseTagQueryUseCase diseaseTagQueryUseCase;

    @GetMapping
    public ResponseEntity<DefaultTypeDiseaseTagListResponse> getDiseaseTagExceptCustomType() {
        return ResponseEntity.ok(DefaultTypeDiseaseTagListResponse.from(diseaseTagQueryUseCase.getDiseaseTagExceptCustomType()));
    }
}
