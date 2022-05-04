package com.comebackhome.calendar.domain.diseasetag.service;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;

public interface DiseaseTagQueryUseCase {
    DiseaseTagListResponseDto getDiseaseTagExceptCustomType();
}
