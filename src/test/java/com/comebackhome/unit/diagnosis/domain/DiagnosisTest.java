package com.comebackhome.unit.diagnosis.domain;

import com.comebackhome.diagnosis.domain.Diagnosis;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisTest {

    @Test
    void 정적_메서드_from으로_생성() throws Exception{

        //when
        Diagnosis result = Diagnosis.from(1L);

        //then
        assertThat(result.getUser().getId()).isEqualTo(1L);
    }
}
