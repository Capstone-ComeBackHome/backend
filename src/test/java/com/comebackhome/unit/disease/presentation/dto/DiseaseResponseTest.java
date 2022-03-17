package com.comebackhome.unit.disease.presentation.dto;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.presentation.dto.DiseaseResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.DiseaseGivenHelper.givenDiseaseResponseDto;
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
        assertThat(result.getCauseList().size()).isEqualTo(2);
        assertThat(result.getHospitalCare()).isEqualTo(diseaseResponseDto.getHospitalCare());
        assertThat(result.getHomeCareList().size()).isEqualTo(2);
        assertThat(result.getComplications()).isEqualTo(diseaseResponseDto.getComplications());
    }
}
