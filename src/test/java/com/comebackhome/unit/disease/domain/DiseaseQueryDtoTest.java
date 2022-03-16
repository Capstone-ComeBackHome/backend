package com.comebackhome.unit.disease.domain;

import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.CauseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.HomeCareQueryDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.comebackhome.support.DiseaseGivenHelper.givenDisease;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseQueryDtoTest {

    @Test
    void 정적메서드_of로_DiseaseQueryDto_생성() throws Exception{
        //given
        Disease disease = givenDisease();
        List<CauseQueryDto> causeQueryDtoList = List.of(new CauseQueryDto("원인"));
        List<HomeCareQueryDto> homeCareQueryDtoList= List.of(new HomeCareQueryDto("해결책"));

        //when
        DiseaseQueryDto diseaseQueryDto = DiseaseQueryDto.of(disease, causeQueryDtoList, homeCareQueryDtoList);

        //then
        assertThat(diseaseQueryDto.getHomeCareList().size()).isEqualTo(1);
        assertThat(diseaseQueryDto.getCauseList().size()).isEqualTo(1);
        assertThat(diseaseQueryDto.getDefinition()).isEqualTo(disease.getDefinition());
        assertThat(diseaseQueryDto.getSymptom()).isEqualTo(disease.getSymptom());
        assertThat(diseaseQueryDto.getComplications()).isEqualTo(disease.getComplications());
        assertThat(diseaseQueryDto.getName()).isEqualTo(disease.getName());
        assertThat(diseaseQueryDto.getHospitalCare()).isEqualTo(disease.getHospitalCare());
        assertThat(diseaseQueryDto.getRecommendDepartment()).isEqualTo(disease.getRecommendDepartment());
    }
}
