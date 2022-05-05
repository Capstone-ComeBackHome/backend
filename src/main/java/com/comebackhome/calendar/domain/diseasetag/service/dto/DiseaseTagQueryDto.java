package com.comebackhome.calendar.domain.diseasetag.service.dto;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DiseaseTagQueryDto {

    private DiseaseType diseaseType;

    private String name;

}
