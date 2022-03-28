package com.comebackhome.disease.domain;

import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;

import java.util.List;
import java.util.Optional;

public interface DiseaseRepository {

    Optional<Disease> findDiseaseById(Long diseaseId);

    SimpleDiseaseQueryDto findSimpleDiseaseQueryDtoByName(String diseaseName);

    void saveAll(List<Disease> diseaseList);
}
