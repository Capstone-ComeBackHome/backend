package com.comebackhome.unit.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.ScheduleResponseDto;
import com.comebackhome.calendar.presentation.dto.ScheduleResponse;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleResponseDto;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleResponseTest {

    @Test
    void 정적_메서드_from_으로_생성() throws Exception{
        //given
        ScheduleResponseDto scheduleResponseDto = givenScheduleResponseDto();

        //when
        ScheduleResponse result = ScheduleResponse.from(scheduleResponseDto);

        //then
        assertThat(result.getScheduleId()).isEqualTo(scheduleResponseDto.getScheduleId());
        assertThat(result.getLocalDate()).isEqualTo(scheduleResponseDto.getLocalDate());
        assertThat(result.getDiseaseTagResponseList().size()).isEqualTo(scheduleResponseDto.getDiseaseTagDtoList().size());
        assertThat(result.getDailyNote()).isEqualTo(scheduleResponseDto.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(scheduleResponseDto.getPainType());
    }
}
