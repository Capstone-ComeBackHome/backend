package com.comebackhome.unit.disease.application;

import com.comebackhome.disease.application.DiseaseService;
import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.DiseaseRepository;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDiseaseQueryDto;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenSimpleDiseaseQueryDto;
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

    @Test
    void diseaseNameList에_있는_이름들로_SimpleDiseaseResponseDto_찾기() throws Exception{
        //given
        List<String> diseaseNameList = List.of("부정맥");
        SimpleDiseaseQueryDto simpleDiseaseQueryDto = givenSimpleDiseaseQueryDto(diseaseNameList.get(0));
        given(diseaseRepository.findSimpleDiseaseQueryDtoByName(any()))
                .willReturn(simpleDiseaseQueryDto);

        //when
        List<SimpleDiseaseResponseDto> result = diseaseService.getSimpleDiseaseList(diseaseNameList);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDiseaseId()).isEqualTo(simpleDiseaseQueryDto.getDiseaseId());
        assertThat(result.get(0).getName()).isEqualTo(simpleDiseaseQueryDto.getName());
        assertThat(result.get(0).getDefinition()).isEqualTo(simpleDiseaseQueryDto.getDefinition());
        assertThat(result.get(0).getRecommendDepartment()).isEqualTo(simpleDiseaseQueryDto.getRecommendDepartment());
    }
}
