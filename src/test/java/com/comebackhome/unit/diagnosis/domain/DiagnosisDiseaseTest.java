package com.comebackhome.unit.diagnosis.domain;

import com.comebackhome.diagnosis.domain.diagnosis.DiagnosisDisease;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisDiseaseTest {

    @Test
    void 정적_메서드_of으로_생성하기() throws Exception{

        //when
        DiagnosisDisease result = DiagnosisDisease.of(1L, 1L);

        //then
        assertThat(result.getDiagnosis().getId()).isEqualTo(1L);
        assertThat(result.getDisease().getId()).isEqualTo(1L);
    }
}
