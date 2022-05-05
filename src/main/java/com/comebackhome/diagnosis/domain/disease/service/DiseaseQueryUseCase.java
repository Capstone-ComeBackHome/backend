package com.comebackhome.diagnosis.domain.disease.service;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;

import java.util.List;

public interface DiseaseQueryUseCase {

    DiseaseResponseDto getDisease(Long diseaseId);

    List<SimpleDiseaseResponseDto> getSimpleDiseaseList(List<String> diseaseNameList);

}
