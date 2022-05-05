package com.comebackhome.diagnosis.presentation;

import com.comebackhome.common.CommonResponse;
import com.comebackhome.diagnosis.application.DiseaseFacade;
import com.comebackhome.diagnosis.domain.disease.service.dto.request.DiseaseSaveRequestDto;
import com.comebackhome.diagnosis.presentation.dto.request.DiseaseSaveRequest;
import com.comebackhome.diagnosis.presentation.dto.response.DiseaseResponse;
import com.comebackhome.diagnosis.presentation.dto.response.SimpleDiseaseResponseList;
import com.comebackhome.diagnosis.presentation.util.CSVUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/v1/diseases")
@RequiredArgsConstructor
public class DiseaseRestController {

    private final DiseaseFacade diseaseFacade;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{diseaseId}")
    public ResponseEntity<CommonResponse<DiseaseResponse>> getDisease(@PathVariable Long diseaseId){
        DiseaseResponse diseaseResponse = DiseaseResponse.from(diseaseFacade.getDisease(diseaseId));
        return ResponseEntity.ok(CommonResponse.success(diseaseResponse));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<CommonResponse<SimpleDiseaseResponseList>> getSimpleDisease
                            (@RequestParam @NotEmpty List<@NotBlank(message = "질병명이 빈칸입니다.") String> diseaseNameList){

        SimpleDiseaseResponseList simpleDiseaseResponseList = SimpleDiseaseResponseList
                .from(diseaseFacade.getSimpleDiseaseList(diseaseNameList));
        return ResponseEntity.ok(CommonResponse.success(simpleDiseaseResponseList));
    }

    @PostMapping
    public ResponseEntity<Void> createDisease(@RequestPart MultipartFile file){
        diseaseFacade.createDisease(getDiseaseSaveRequestDtoList(file));
        return ResponseEntity.ok().build();
    }

    private List<DiseaseSaveRequestDto> getDiseaseSaveRequestDtoList(MultipartFile file) {
        return CSVUtil.toDiseaseSaveRequest(file).stream()
                .map(DiseaseSaveRequest::toDiseaseSaveRequestDto)
                .collect(Collectors.toList());
    }


}
