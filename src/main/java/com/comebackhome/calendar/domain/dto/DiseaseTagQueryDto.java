package com.comebackhome.calendar.domain.dto;

import com.comebackhome.calendar.domain.DiseaseType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DiseaseTagQueryDto {

    private DiseaseType diseaseType;

    private String name;
}
