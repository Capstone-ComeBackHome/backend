package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.calendar.domain.schedule.repository.dto.LineQueryDto;
import com.comebackhome.calendar.presentation.dto.response.LineResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class LineResponseTest {

    @Test
    void 정적_메서드_from() {
        //given
        LineQueryDto lineQueryDto = new LineQueryDto(LocalDate.now(), PainType.BAD, "재채기");

        //when
        LineResponse result = LineResponse.from(lineQueryDto);

        //then
        assertThat(result.getPainType()).isEqualTo(lineQueryDto.getPainType());
        assertThat(result.getDiseaseName()).isEqualTo(lineQueryDto.getDiseaseName());
        assertThat(result.getScheduleDate()).isEqualTo(lineQueryDto.getScheduleDate());

    }
}
