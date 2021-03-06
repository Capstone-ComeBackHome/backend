package com.comebackhome.calendar.domain.diseasetag.service.dto;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DiseaseTagResponseDto {

    private String diseaseType;

    private String name;

    public static DiseaseTagResponseDto from(DiseaseTag diseaseTag){
        return DiseaseTagResponseDto.builder()
                .diseaseType(diseaseTag.getDiseaseType().name())
                .name(diseaseTag.getName())
                .build();
    }
}
