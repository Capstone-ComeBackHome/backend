package com.comebackhome.disease.application;

import com.comebackhome.disease.application.dto.DiseaseRequestDto;

import java.util.List;

public interface DiseaseCommandUseCase {

    void createDisease(List<DiseaseRequestDto> diseaseRequestDto);
}
