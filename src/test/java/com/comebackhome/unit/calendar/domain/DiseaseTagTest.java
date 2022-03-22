package com.comebackhome.unit.calendar.domain;

import com.comebackhome.calendar.domain.DiseaseTag;
import org.junit.jupiter.api.Test;

import static com.comebackhome.calendar.domain.DiseaseType.HEAD;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagTest {

    @Test
    void 정적_메서드_of로_생성() throws Exception{
        //when
        DiseaseTag diseaseTag = DiseaseTag.of(HEAD, "두통");

        //then
        assertThat(diseaseTag.getDiseaseType()).isEqualTo(HEAD);
        assertThat(diseaseTag.getName()).isEqualTo("두통");
    }
}
