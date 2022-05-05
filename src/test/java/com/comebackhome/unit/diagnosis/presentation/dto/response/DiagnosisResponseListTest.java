package com.comebackhome.unit.diagnosis.presentation.dto.response;

import com.comebackhome.diagnosis.domain.service.dto.DiagnosisResponseDtoList;
import com.comebackhome.diagnosis.presentation.dto.response.DiagnosisResponseList;
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
