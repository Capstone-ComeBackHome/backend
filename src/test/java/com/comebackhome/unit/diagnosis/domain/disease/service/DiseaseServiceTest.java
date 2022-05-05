package com.comebackhome.unit.diagnosis.domain.disease.service;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.repository.DiseaseRepository;
import com.comebackhome.diagnosis.domain.disease.service.DiseaseService;
import com.comebackhome.diagnosis.domain.disease.service.dto.request.DiseaseSaveRequestDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
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
        given(diseaseRepository.findById(any())).willReturn(Optional.of(disease));

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
        given(diseaseRepository.findById(any())).willReturn(Optional.empty());

        //when then
        assertThatThrownBy(
                () -> diseaseService.getDisease(1L))
                .isInstanceOf(DiseaseNotFoundException.class);

    }

    @Test
    void diseaseNameList에_있는_이름들로_SimpleDiseaseResponseDto_찾기() throws Exception{
        //given
        List<String> diseaseNameList = List.of("부정맥");
        SimpleDiseaseResponseDto simpleDiseaseResponseDto = givenSimpleDiseaseResponseDto(diseaseNameList.get(0),1L);
        given(diseaseRepository.findSimpleDiseaseResponseDtoByName(any()))
                .willReturn(simpleDiseaseResponseDto);

        //when
        List<SimpleDiseaseResponseDto> result = diseaseService.getSimpleDiseaseList(diseaseNameList);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDiseaseId()).isEqualTo(simpleDiseaseResponseDto.getDiseaseId());
        assertThat(result.get(0).getName()).isEqualTo(simpleDiseaseResponseDto.getName());
        assertThat(result.get(0).getDefinition()).isEqualTo(simpleDiseaseResponseDto.getDefinition());
        assertThat(result.get(0).getRecommendDepartment()).isEqualTo(simpleDiseaseResponseDto.getRecommendDepartment());
    }

    @Test
    void disease_저장하기() throws Exception{
        // given
        List<DiseaseSaveRequestDto> diseaseSaveRequestDtoList = List.of(givenDiseaseRequestDto());

        //when
        diseaseService.createDisease(diseaseSaveRequestDtoList);

        //then
        then(diseaseRepository).should().saveAll(any());
    }

}
