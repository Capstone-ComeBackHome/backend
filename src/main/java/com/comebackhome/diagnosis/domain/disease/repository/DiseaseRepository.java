package com.comebackhome.diagnosis.domain.disease.repository;

import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface DiseaseRepository {

    Optional<Disease> findById(Long diseaseId);

    @Cacheable(value = "simpleDisease",
            key = "#diseaseName",
            unless = "#result == null"
    )
    SimpleDiseaseResponseDto findSimpleDiseaseResponseDtoByName(String diseaseName);

    void saveAll(List<Disease> diseaseList);

    Optional<Long> findIdByName(String diseaseName);
}
