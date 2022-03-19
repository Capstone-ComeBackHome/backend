package com.comebackhome.calendar.domain;

import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;

import java.util.List;

public interface DiseaseTagRepository {

    List<DiseaseTagQueryDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType);
}
