package com.comebackhome.calendar.application;

import com.comebackhome.calendar.application.dto.response.DiseaseTagResponseDto;

public interface DiseaseTagQueryUseCase {
    DiseaseTagResponseDto getDiseaseTagExceptCustomType();
}
