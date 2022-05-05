package com.comebackhome.diagnosis.domain.disease.service;

import com.comebackhome.diagnosis.domain.disease.service.dto.request.DiseaseSaveRequestDto;

import java.util.List;

public interface DiseaseCommandUseCase {

    void createDisease(List<DiseaseSaveRequestDto> diseaseSaveRequestDto);
}
