package com.comebackhome.unit.disease.presentation.dto;

import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.presentation.dto.SimpleDiseaseResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.DiseaseGivenHelper.givenSimpleDiseaseResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleDiseaseResponseTest {

    @Test
    void 정적메세드_from으로_생성() throws Exception{
        //given
        SimpleDiseaseResponseDto simpleDiseaseResponseDto = givenSimpleDiseaseResponseDto();

        //when
        SimpleDiseaseResponse result = SimpleDiseaseResponse.from(simpleDiseaseResponseDto);

        //then
        assertThat(result.getName()).isEqualTo(simpleDiseaseResponseDto.getName());
        assertThat(result.getDefinition()).isEqualTo(simpleDiseaseResponseDto.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(simpleDiseaseResponseDto.getRecommendDepartment());
    }
}
