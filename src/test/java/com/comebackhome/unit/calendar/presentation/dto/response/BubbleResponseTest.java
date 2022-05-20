package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.service.dto.response.BubbleResponseDto;
import com.comebackhome.calendar.presentation.dto.response.BubbleResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BubbleResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() {
        //given
        DiseaseType type = DiseaseType.CUSTOM;
        int count = 0;
        double avg = 1.0;

        //when
        BubbleResponse result = BubbleResponse.from(new BubbleResponseDto(type, count, avg));

        //then
        assertThat(result.getDiseaseType()).isEqualTo(type);
        assertThat(result.getCount()).isEqualTo(count);
        assertThat(result.getPainAverage()).isEqualTo(avg);

    }
}
