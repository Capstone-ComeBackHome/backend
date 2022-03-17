package com.comebackhome.unit.disease.application.dto;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDiseaseQueryDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseResponseDtoTest {

    @Test
    void 정적메서드_from_DiseaseResponseDto_생성() throws Exception{
        //given
        DiseaseQueryDto diseaseQueryDto = givenDiseaseQueryDto();

        //when
        DiseaseResponseDto result = DiseaseResponseDto.from(diseaseQueryDto);

        //then
        assertThat(result.getName()).isEqualTo(diseaseQueryDto.getName());
        assertThat(result.getDefinition()).isEqualTo(diseaseQueryDto.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(diseaseQueryDto.getRecommendDepartment());
        assertThat(result.getSymptom()).isEqualTo(diseaseQueryDto.getSymptom());
        assertThat(result.getCauseList().size()).isEqualTo(2);
        assertThat(result.getHospitalCare()).isEqualTo(diseaseQueryDto.getHospitalCare());
        assertThat(result.getHomeCareList().size()).isEqualTo(2);
        assertThat(result.getComplications()).isEqualTo(diseaseQueryDto.getComplications());
    }
}
