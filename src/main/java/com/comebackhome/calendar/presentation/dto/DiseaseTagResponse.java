package com.comebackhome.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.DiseaseTagDto;
import com.comebackhome.calendar.domain.DiseaseType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagResponse {

    private DiseaseType diseaseType;

    private String name;

    public static DiseaseTagResponse from(DiseaseTagDto diseaseTagDto){
        return DiseaseTagResponse.builder()
                .diseaseType(diseaseTagDto.getDiseaseType())
                .name(diseaseTagDto.getName())
                .build();
    }
}
