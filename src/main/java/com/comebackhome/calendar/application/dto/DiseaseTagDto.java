package com.comebackhome.calendar.application.dto;

import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagDto {

    private DiseaseType diseaseType;

    private String name;

    public static DiseaseTagDto from(DiseaseTagQueryDto diseaseTagQueryDto){
        return DiseaseTagDto.builder()
                .diseaseType(diseaseTagQueryDto.getDiseaseType())
                .name(diseaseTagQueryDto.getName())
                .build();
    }
}
