package com.comebackhome.unit.calendar.domain.diseasetag;

import com.comebackhome.calendar.domain.diseasetag.ScheduleDiseaseTag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleDiseaseTagTest {

    @Test
    void 정적_메서드_of로_생성() throws Exception{
        //given

        //when
        ScheduleDiseaseTag result = ScheduleDiseaseTag.of(1L, 2L);

        //then
        assertThat(result.getSchedule().getId()).isEqualTo(1L);
        assertThat(result.getDiseaseTag().getId()).isEqualTo(2L);
    }
}
