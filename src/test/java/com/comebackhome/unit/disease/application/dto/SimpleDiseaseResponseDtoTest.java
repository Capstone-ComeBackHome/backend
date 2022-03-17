package com.comebackhome.unit.disease.application.dto;

import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenSimpleDiseaseQueryDto;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleDiseaseResponseDtoTest {

    @Test
    void 정적메서드_from으로_생성() throws Exception{
        //given
        SimpleDiseaseQueryDto simpleDiseaseQueryDto = givenSimpleDiseaseQueryDto();

        //when
        SimpleDiseaseResponseDto result = SimpleDiseaseResponseDto.from(simpleDiseaseQueryDto);

        //then
        assertThat(result.getName()).isEqualTo(simpleDiseaseQueryDto.getName());
        assertThat(result.getDefinition()).isEqualTo(simpleDiseaseQueryDto.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(simpleDiseaseQueryDto.getRecommendDepartment());
    }
}
