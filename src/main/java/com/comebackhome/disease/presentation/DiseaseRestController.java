package com.comebackhome.disease.presentation;

import com.comebackhome.common.CSVUtil;
import com.comebackhome.disease.application.DiseaseCommandUseCase;
import com.comebackhome.disease.application.DiseaseQueryUseCase;
import com.comebackhome.disease.presentation.dto.response.DiseaseResponse;
import com.comebackhome.disease.presentation.dto.response.SimpleDiseaseResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/diseases")
@RequiredArgsConstructor
public class DiseaseRestController {

    private final DiseaseQueryUseCase diseaseQueryUseCase;
    private final DiseaseCommandUseCase diseaseCommandUseCase;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<DiseaseResponse> getDisease(@RequestParam Long diseaseId){
        return ResponseEntity.ok(DiseaseResponse.from(diseaseQueryUseCase.getDisease(diseaseId)));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/simple")
    public ResponseEntity<SimpleDiseaseResponseList> getSimpleDisease
                            (@RequestParam @NotEmpty List<@NotBlank(message = "질병명이 빈칸입니다.") String> diseaseNameList){

        return ResponseEntity.ok(SimpleDiseaseResponseList
                .from(diseaseQueryUseCase.getSimpleDiseaseList(diseaseNameList)));
    }

    @PostMapping
    public ResponseEntity<Void> createDisease(@RequestPart MultipartFile file){
        diseaseCommandUseCase.createDisease(CSVUtil.toDiseaseRequestDto(file));
        return ResponseEntity.ok().build();
    }



}
