package com.comebackhome.unit.disease.presentation.dto;

import com.comebackhome.disease.application.dto.DiagnosisResponseDtoList;
import com.comebackhome.disease.presentation.dto.DiagnosisResponseList;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisResponseDtoList;
import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisResponseListTest {

    @Test
    void 정적_메서드_from으로_생성() throws Exception{
        //given
        DiagnosisResponseDtoList diagnosisResponseDtoList = givenDiagnosisResponseDtoList();

        //when
        DiagnosisResponseList result = DiagnosisResponseList.from(diagnosisResponseDtoList);

        //then
        assertThat(result.isHasNext()).isFalse();
        assertThat(result.getDiagnosisResponseList().size()).isEqualTo(3);
    }
}
