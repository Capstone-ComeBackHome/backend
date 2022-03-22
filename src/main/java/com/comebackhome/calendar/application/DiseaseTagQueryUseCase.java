package com.comebackhome.calendar.application;

import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;

public interface DiseaseTagQueryUseCase {
    DiseaseTagResponseDto getDiseaseTagExceptCustomType();
}
