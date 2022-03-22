package com.comebackhome.calendar.application.dto;

import com.comebackhome.calendar.domain.DiseaseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagRequestDto {

    private DiseaseType diseaseType;

    private String name;
}
