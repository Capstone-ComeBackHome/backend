package com.comebackhome.calendar.domain.diseasetag.service;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DefaultTypeDiseaseTagListResponseDto;
import org.springframework.cache.annotation.Cacheable;

public interface DiseaseTagQueryUseCase {

    @Cacheable(value = "diseaseTag",
            key = "'tags'",
            unless = "#result == null"
    )
    DefaultTypeDiseaseTagListResponseDto getDiseaseTagExceptCustomType();
}
