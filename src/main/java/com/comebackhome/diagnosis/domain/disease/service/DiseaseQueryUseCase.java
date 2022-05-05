package com.comebackhome.diagnosis.domain.disease.service;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface DiseaseQueryUseCase {

    @Cacheable(value = "disease",
            key = "#diseaseId",
            unless = "#result == null"
    )
    DiseaseResponseDto getDisease(Long diseaseId);

    List<SimpleDiseaseResponseDto> getSimpleDiseaseList(List<String> diseaseNameList);

}
