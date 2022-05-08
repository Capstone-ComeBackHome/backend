package com.comebackhome.unit.diagnosis.presentation.dto.response;

import com.comebackhome.diagnosis.domain.diagnosis.service.dto.DiagnosisResponseDto;
import com.comebackhome.diagnosis.presentation.dto.response.DiagnosisResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisResponseTest {

    @Test
    void 정적_메서드_from_생성하기() throws Exception{
        //given
        DiagnosisResponseDto diagnosisResponseDto = givenDiagnosisResponseDto(1L);

        //when
        DiagnosisResponse result = DiagnosisResponse.from(diagnosisResponseDto);

        //then
        assertThat(result.getDiagnosisId()).isEqualTo(diagnosisResponseDto.getDiagnosisId());
        assertThat(result.getCreatedDate()).isEqualTo(diagnosisResponseDto.getCreatedDate());
        assertThat(result.getDiseaseNameList().size()).isEqualTo(diagnosisResponseDto.getDiseaseNameList().size());
    }
}
