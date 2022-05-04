package com.comebackhome.disease.presentation.dto.response;

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

    private Long diseaseId;

    private String name;

    private String definition;

    private String recommendDepartment;

    public static SimpleDiseaseResponse from(SimpleDiseaseResponseDto simpleDiseaseResponseDto){
        return SimpleDiseaseResponse.builder()
                .diseaseId(simpleDiseaseResponseDto.getDiseaseId())
                .name(simpleDiseaseResponseDto.getName())
                .definition(simpleDiseaseResponseDto.getDefinition())
                .recommendDepartment(simpleDiseaseResponseDto.getRecommendDepartment())
                .build();
    }
}
