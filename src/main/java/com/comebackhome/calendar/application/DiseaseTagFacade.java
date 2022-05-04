package com.comebackhome.calendar.application;

import com.comebackhome.calendar.domain.diseasetag.service.DiseaseTagQueryUseCase;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiseaseTagFacade {

    private final DiseaseTagQueryUseCase diseaseTagQueryUseCase;

    public DiseaseTagListResponseDto getDiseaseTagExceptCustomType() {
        return diseaseTagQueryUseCase.getDiseaseTagExceptCustomType();
    }

}
