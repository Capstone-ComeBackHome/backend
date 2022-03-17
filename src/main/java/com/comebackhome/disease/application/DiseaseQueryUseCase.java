package com.comebackhome.disease.application;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;

import java.util.List;

public interface DiseaseQueryUseCase {

    DiseaseResponseDto getDisease(Long diseaseId);

    List<SimpleDiseaseResponseDto> getSimpleDiseaseList(List<String> diseaseNameList);

}
