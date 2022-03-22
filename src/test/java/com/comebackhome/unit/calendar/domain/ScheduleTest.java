package com.comebackhome.unit.calendar.domain;

import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.support.helper.CalendarGivenHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScheduleTest {

    @Test
    void 정적_메서드_from으로_생성() throws Exception{
        //given
        ScheduleSaveRequestDto scheduleSaveRequestDto = CalendarGivenHelper.givenScheduleSaveRequestDto(1L);

        //when
        Schedule result = Schedule.from(scheduleSaveRequestDto);

        //then
        Assertions.assertThat(result.getUser().getId()).isEqualTo(scheduleSaveRequestDto.getUserId());
        Assertions.assertThat(result.getDailyNote()).isEqualTo(scheduleSaveRequestDto.getDailyNote());
        Assertions.assertThat(result.getLocalDate()).isEqualTo(scheduleSaveRequestDto.getLocalDate());
        Assertions.assertThat(result.getPainType()).isEqualTo(scheduleSaveRequestDto.getPainType());
    }
}
