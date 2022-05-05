package com.comebackhome.calendar.application;

import com.comebackhome.calendar.domain.diseasetag.service.DiseaseTagQueryUseCase;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DefaultTypeDiseaseTagListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiseaseTagFacade {

    private final DiseaseTagQueryUseCase diseaseTagQueryUseCase;

    public DefaultTypeDiseaseTagListResponseDto getDiseaseTagExceptCustomType() {
        return diseaseTagQueryUseCase.getDiseaseTagExceptCustomType();
    }

}
