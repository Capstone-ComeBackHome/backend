package com.comebackhome.diagnosis.domain.disease.repository;

import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public interface DiseaseRepository {

    // todo disease 지운다.
    Optional<Disease> findDiseaseById(Long diseaseId);

    @Cacheable(value = "simpleDisease",
            key = "#diseaseName",
            unless = "#result == null"
    )
    SimpleDiseaseResponseDto findSimpleDiseaseQueryDtoByName(String diseaseName);

    void saveAll(List<Disease> diseaseList);

    Optional<Long> findIdByName(String diseaseName);
}
