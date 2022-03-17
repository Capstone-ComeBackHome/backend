package com.comebackhome.disease.presentation.dto;

import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDiseaseResponse {

    private String name;

    private String definition;

    private String recommendDepartment;

    public static SimpleDiseaseResponse from(SimpleDiseaseResponseDto simpleDiseaseResponseDto){
        return SimpleDiseaseResponse.builder()
                .name(simpleDiseaseResponseDto.getName())
                .definition(simpleDiseaseResponseDto.getDefinition())
                .recommendDepartment(simpleDiseaseResponseDto.getRecommendDepartment())
                .build();
    }
}
