package com.comebackhome.disease.application.dto;


import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SimpleDiseaseResponseDto {

    private String name;

    private String definition;

    private String recommendDepartment;

    public static SimpleDiseaseResponseDto from(SimpleDiseaseQueryDto simpleDiseaseQueryDto){
        return SimpleDiseaseResponseDto.builder()
                .name(simpleDiseaseQueryDto.getName())
                .definition(simpleDiseaseQueryDto.getDefinition())
                .recommendDepartment(simpleDiseaseQueryDto.getRecommendDepartment())
                .build();
    }
}
