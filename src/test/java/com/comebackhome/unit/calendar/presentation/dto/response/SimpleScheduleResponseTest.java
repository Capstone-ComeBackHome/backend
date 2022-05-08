package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.service.dto.response.SimpleScheduleResponseDto;
import com.comebackhome.calendar.presentation.dto.response.SimpleScheduleResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSimpleScheduleResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleScheduleResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        SimpleScheduleResponseDto simpleScheduleResponseDto = givenSimpleScheduleResponseDto(1L, LocalDate.now(), 3);

        //when
        SimpleScheduleResponse result = SimpleScheduleResponse.from(simpleScheduleResponseDto);

        //then
        assertThat(result.getScheduleId()).isEqualTo(simpleScheduleResponseDto.getScheduleId());
        assertThat(result.getLocalDate()).isEqualTo(simpleScheduleResponseDto.getLocalDate());
        assertThat(result.getDiseaseTagCount()).isEqualTo(simpleScheduleResponseDto.getDiseaseTagCount());
    }
}
