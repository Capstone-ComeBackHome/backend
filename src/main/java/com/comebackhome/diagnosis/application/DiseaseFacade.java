package com.comebackhome.diagnosis.application;

import com.comebackhome.diagnosis.domain.disease.service.DiseaseCommandUseCase;
import com.comebackhome.diagnosis.domain.disease.service.DiseaseQueryUseCase;
import com.comebackhome.diagnosis.domain.disease.service.dto.request.DiseaseSaveRequestDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseFacade {

    private final DiseaseCommandUseCase diseaseCommandUseCase;
    private final DiseaseQueryUseCase diseaseQueryUseCase;

    public DiseaseResponseDto getDisease(Long diseaseId) {
        return diseaseQueryUseCase.getDisease(diseaseId);
    }

    public List<SimpleDiseaseResponseDto> getSimpleDiseaseList(List<String> diseaseNameList) {
        return diseaseQueryUseCase.getSimpleDiseaseList(diseaseNameList);
    }

    public void createDisease(List<DiseaseSaveRequestDto> diseaseSaveRequestDto) {
        diseaseCommandUseCase.createDisease(diseaseSaveRequestDto);
    }
}
