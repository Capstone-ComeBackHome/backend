package com.comebackhome.disease.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleDiseaseQueryDto {

    private Long diseaseId;

    private String name;

    private String definition;

    private String recommendDepartment;
}
