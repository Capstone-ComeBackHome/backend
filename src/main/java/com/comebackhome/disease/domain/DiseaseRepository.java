package com.comebackhome.disease.domain;

import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;

public interface DiseaseRepository {

    DiseaseQueryDto findDiseaseQueryDtoById(Long diseaseId);

    SimpleDiseaseQueryDto findSimpleDiseaseQueryDtoByName(String diseaseName);
}
