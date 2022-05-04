package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagResponse {

    private DiseaseType diseaseType;

    private String name;

    public static DiseaseTagResponse from(DiseaseTagResponseDto diseaseTagResponseDto){
        return DiseaseTagResponse.builder()
                .diseaseType(diseaseTagResponseDto.getDiseaseType())
                .name(diseaseTagResponseDto.getName())
                .build();
    }
}
