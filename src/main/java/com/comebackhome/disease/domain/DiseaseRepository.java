package com.comebackhome.disease.domain;

import com.comebackhome.disease.domain.dto.DiseaseQueryDto;

public interface DiseaseRepository {

    DiseaseQueryDto findDiseaseQueryDtoById(Long diseaseId);
}
