package com.comebackhome.unit.diagnosis.domain.disease.service.dto;

import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseResponseDtoTest {

    @Test
    void 정적메서드_from_DiseaseResponseDto_생성() throws Exception{
        //given
        Disease disease = givenDisease();

        //when
        DiseaseResponseDto result = DiseaseResponseDto.from(disease);

        //then
        assertThat(result.getName()).isEqualTo(disease.getName());
        assertThat(result.getDefinition()).isEqualTo(disease.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(disease.getRecommendDepartment());
        assertThat(result.getSymptom()).isEqualTo(disease.getSymptom());
        assertThat(result.getCause()).isEqualTo(disease.getCause());
        assertThat(result.getHospitalCare()).isEqualTo(disease.getHospitalCare());
    }
}
