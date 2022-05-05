package com.comebackhome.unit.diagnosis.application;

import com.comebackhome.diagnosis.application.DiseaseFacade;
import com.comebackhome.diagnosis.domain.disease.service.DiseaseCommandUseCase;
import com.comebackhome.diagnosis.domain.disease.service.DiseaseQueryUseCase;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDiseaseResponseDto;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenSimpleDiseaseResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DiseaseFacadeTest {

    @InjectMocks DiseaseFacade diseaseFacade;
    @Mock DiseaseCommandUseCase diseaseCommandUseCase;
    @Mock DiseaseQueryUseCase diseaseQueryUseCase;

    @Test
    void 질병_조회() {
        //given
        DiseaseResponseDto dto = givenDiseaseResponseDto();
        given(diseaseQueryUseCase.getDisease(any())).willReturn(dto);

        //when
        DiseaseResponseDto result = diseaseFacade.getDisease(any());

        //then
        assertThat(result.getCause()).isEqualTo(dto.getCause());
        assertThat(result.getDefinition()).isEqualTo(dto.getDefinition());
        assertThat(result.getName()).isEqualTo(dto.getName());
        assertThat(result.getHospitalCare()).isEqualTo(dto.getHospitalCare());
        assertThat(result.getSymptom()).isEqualTo(dto.getSymptom());
        assertThat(result.getRecommendDepartment()).isEqualTo(dto.getRecommendDepartment());
    }

    @Test
    void 질병_심플_조회() {
        //given
        SimpleDiseaseResponseDto dto = givenSimpleDiseaseResponseDto();
        given(diseaseQueryUseCase.getSimpleDiseaseList(any())).willReturn(List.of(dto));

        //when
        List<SimpleDiseaseResponseDto> result = diseaseFacade.getSimpleDiseaseList(any());

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(dto.getName());
        assertThat(result.get(0).getDiseaseId()).isEqualTo(dto.getDiseaseId());
        assertThat(result.get(0).getRecommendDepartment()).isEqualTo(dto.getRecommendDepartment());
        assertThat(result.get(0).getDefinition()).isEqualTo(dto.getDefinition());
    }
}


