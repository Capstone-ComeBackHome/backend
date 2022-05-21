package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.presentation.dto.response.ScheduleResponse;
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
        assertThat(result.getScheduleDate()).isEqualTo(scheduleResponseDto.getScheduleDate());
        assertThat(result.getDiseaseTagResponseList().size()).isEqualTo(scheduleResponseDto.getDiseaseTagResponseDtoList().size());
        assertThat(result.getDailyNote()).isEqualTo(scheduleResponseDto.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(scheduleResponseDto.getPainType());
    }
}
