package com.comebackhome.unit.disease.application;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.application.DiseaseService;
import com.comebackhome.disease.application.dto.DiseaseRequestDto;
import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.repository.DiseaseRepository;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.support.helper.DiseaseGivenHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class DiseaseServiceTest {

    @InjectMocks DiseaseService diseaseService;
    @Mock DiseaseRepository diseaseRepository;

    @Test
    void diseaseId로_DiseaseResponseDto_가져오기() throws Exception{
        //given
        Disease disease = givenDisease();
        given(diseaseRepository.findDiseaseById(any())).willReturn(Optional.of(disease));

        //when
        DiseaseResponseDto result = diseaseService.getDisease(1L);

        //then
        assertThat(result.getName()).isEqualTo(disease.getName());
        assertThat(result.getDefinition()).isEqualTo(disease.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(disease.getRecommendDepartment());
        assertThat(result.getSymptom()).isEqualTo(disease.getSymptom());
        assertThat(result.getCause()).isEqualTo(disease.getCause());
        assertThat(result.getHospitalCare()).isEqualTo(disease.getHospitalCare());
    }

    @Test
    void 없는_diseaseId로_DiseaseResponseDto_가져오기() throws Exception{
        //given
        given(diseaseRepository.findDiseaseById(any())).willReturn(Optional.empty());

        //when then
        assertThatThrownBy(
                () -> diseaseService.getDisease(1L))
                .isInstanceOf(DiseaseNotFoundException.class);

    }

    @Test
    void diseaseNameList에_있는_이름들로_SimpleDiseaseResponseDto_찾기() throws Exception{
        //given
        List<String> diseaseNameList = List.of("부정맥");
        SimpleDiseaseQueryDto simpleDiseaseQueryDto = givenSimpleDiseaseQueryDto(diseaseNameList.get(0),1L);
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

    @Test
    void disease_저장하기() throws Exception{
        // given
        List<DiseaseRequestDto> diseaseRequestDtoList = List.of(givenDiseaseRequestDto());

        //when
        diseaseService.createDisease(diseaseRequestDtoList);

        //then
        then(diseaseRepository).should().saveAll(any());
    }

}
