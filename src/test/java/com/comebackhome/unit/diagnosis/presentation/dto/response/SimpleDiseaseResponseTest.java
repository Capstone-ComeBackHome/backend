package com.comebackhome.unit.diagnosis.presentation.dto.response;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import com.comebackhome.diagnosis.presentation.dto.response.SimpleDiseaseResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenSimpleDiseaseResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleDiseaseResponseTest {

    @Test
    void 정적메세드_from으로_생성() throws Exception{
        //given
        SimpleDiseaseResponseDto simpleDiseaseResponseDto = givenSimpleDiseaseResponseDto();

        //when
        SimpleDiseaseResponse result = SimpleDiseaseResponse.from(simpleDiseaseResponseDto);

        //then
        assertThat(result.getDiseaseId()).isEqualTo(simpleDiseaseResponseDto.getDiseaseId());
        assertThat(result.getName()).isEqualTo(simpleDiseaseResponseDto.getName());
        assertThat(result.getDefinition()).isEqualTo(simpleDiseaseResponseDto.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(simpleDiseaseResponseDto.getRecommendDepartment());
    }
}
