package com.comebackhome.unit.calendar.presentation.dto;

import com.comebackhome.calendar.application.dto.ScheduleModifyRequestDto;
import com.comebackhome.calendar.presentation.dto.ScheduleModifyRequest;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleModifyRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleModifyRequestTest {

    @Test
    void ScheduleModifyRequestDto로_전환하기 () throws Exception{
        //given
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();

        //when
        ScheduleModifyRequestDto result = scheduleModifyRequest.toScheduleModifyRequestDto();

        //then
        assertThat(result.getDiseaseTagRequestDtoList().size()).isEqualTo(scheduleModifyRequest.getDiseaseTagRequestList().size());
        assertThat(result.getDailyNote()).isEqualTo(scheduleModifyRequest.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(scheduleModifyRequest.getPainType());
    }
}
