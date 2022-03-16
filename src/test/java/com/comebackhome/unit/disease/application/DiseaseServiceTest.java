package com.comebackhome.unit.disease.application;

import com.comebackhome.disease.application.DiseaseService;
import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.domain.DiseaseRepository;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.comebackhome.support.DiseaseGivenHelper.givenDiseaseQueryDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DiseaseServiceTest {

    @InjectMocks DiseaseService diseaseService;
    @Mock DiseaseRepository diseaseRepository;

    @Test
    void diseaseId로_DiseaseResponseDto_가져오기() throws Exception{
        //given
        DiseaseQueryDto diseaseQueryDto = givenDiseaseQueryDto();
        given(diseaseRepository.findDiseaseQueryDtoById(any())).willReturn(diseaseQueryDto);

        //when
        DiseaseResponseDto result = diseaseService.getDisease(1L);

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
