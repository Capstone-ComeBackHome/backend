package com.comebackhome.calendar.domain.service.dto.request;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagRequestDto {

    private DiseaseType diseaseType;

    private String name;
}
