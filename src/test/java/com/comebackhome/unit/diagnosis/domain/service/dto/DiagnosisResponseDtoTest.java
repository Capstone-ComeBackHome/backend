package com.comebackhome.unit.diagnosis.domain.service.dto;

import com.comebackhome.diagnosis.domain.diagnosis.Diagnosis;
import com.comebackhome.diagnosis.domain.diagnosis.service.dto.DiagnosisResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosis;
import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisResponseDtoTest {

    @Test
    void 정적_메서드_of로_생성하기() throws Exception{
        //given
        Diagnosis diagnosis = givenDiagnosis();
        List<String> diseaseNameList = List.of("질병1","질병2","질병3");

        //when
        DiagnosisResponseDto result = DiagnosisResponseDto.of(diagnosis, diseaseNameList);

        //then
        assertThat(result.getDiagnosisId()).isEqualTo(diagnosis.getId());
        assertThat(result.getCreatedDate()).isEqualTo(diagnosis.getCreatedDate());
        assertThat(result.getDiseaseNameList().size()).isEqualTo(3);
    }
}
