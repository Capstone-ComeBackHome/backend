package com.comebackhome.diagnosis.domain.disease.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleDiseaseResponseDto {

    private Long diseaseId;

    private String name;

    private String definition;

    private String recommendDepartment;
}
