package com.comebackhome.disease.domain.repository;

import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;

import java.util.List;
import java.util.Optional;

public interface DiseaseRepository {

    // todo disease 지운다.
    Optional<Disease> findDiseaseById(Long diseaseId);

    SimpleDiseaseQueryDto findSimpleDiseaseQueryDtoByName(String diseaseName);

    void saveAll(List<Disease> diseaseList);

    Optional<Long> findIdByName(String diseaseName);
}
