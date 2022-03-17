package com.comebackhome.disease.presentation;

import com.comebackhome.disease.application.DiseaseQueryUseCase;
import com.comebackhome.disease.presentation.dto.DiseaseResponse;
import com.comebackhome.disease.presentation.dto.SimpleDiseaseResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseQueryUseCase diseaseQueryUseCase;

    @GetMapping
    public ResponseEntity<DiseaseResponse> getDisease(@RequestParam Long diseaseId){
        return ResponseEntity.ok(DiseaseResponse.from(diseaseQueryUseCase.getDisease(diseaseId)));
    }

    @GetMapping("/simple")
    public ResponseEntity<SimpleDiseaseResponseList> getSimpleDisease
                            (@RequestParam @NotEmpty List<@NotBlank(message = "질병명이 빈칸입니다.") String> diseaseNameList){

        return ResponseEntity.ok(SimpleDiseaseResponseList
                .from(diseaseQueryUseCase.getSimpleDiseaseList(diseaseNameList)));
    }

}
