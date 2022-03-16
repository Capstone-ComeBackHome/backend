package com.comebackhome.disease.application;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;

public interface DiseaseQueryUseCase {

    DiseaseResponseDto getDisease(Long diseaseId);

}
