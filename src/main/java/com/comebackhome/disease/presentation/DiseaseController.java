package com.comebackhome.disease.presentation;

import com.comebackhome.disease.application.DiseaseQueryUseCase;
import com.comebackhome.disease.presentation.dto.DiseaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/disease")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseQueryUseCase diseaseQueryUseCase;

    @GetMapping
    public ResponseEntity<DiseaseResponse> getDisease(@RequestParam Long diseaseId){
        return ResponseEntity.ok(DiseaseResponse.from(diseaseQueryUseCase.getDisease(diseaseId)));
    }

}
