package com.comebackhome.unit.diagnosis.presentation.dto.response;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.presentation.dto.response.DiseaseResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDiseaseResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseResponseTest {

    @Test
    void 정적메서드_from으로_생성() throws Exception{
        //given
        DiseaseResponseDto diseaseResponseDto = givenDiseaseResponseDto();

        //when
        DiseaseResponse result = DiseaseResponse.from(diseaseResponseDto);

        //then
        assertThat(result.getName()).isEqualTo(diseaseResponseDto.getName());
        assertThat(result.getDefinition()).isEqualTo(diseaseResponseDto.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(diseaseResponseDto.getRecommendDepartment());
        assertThat(result.getSymptom()).isEqualTo(diseaseResponseDto.getSymptom());
        assertThat(result.getCause()).isEqualTo(diseaseResponseDto.getCause());
        assertThat(result.getHospitalCare()).isEqualTo(diseaseResponseDto.getHospitalCare());
    }
}
