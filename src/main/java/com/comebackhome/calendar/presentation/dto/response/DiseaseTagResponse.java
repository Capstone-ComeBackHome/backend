package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.application.dto.response.DiseaseTagDto;
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
