package com.comebackhome.unit.calendar.presentation.dto.request;

import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleSaveRequestDto;
import com.comebackhome.calendar.presentation.dto.request.ScheduleSaveRequest;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleSaveRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleSaveRequestTest {

    @Test
    void ScheduleSaveRequestDto로_전환하기() throws Exception{
        //given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();

        //when
        ScheduleSaveRequestDto result = scheduleSaveRequest.toScheduleSaveRequestDto(1L);

        //then
        assertThat(result.getDiseaseTagRequestDtoList().size())
                .isEqualTo(scheduleSaveRequest.getDiseaseTagRequestList().size());
        assertThat(result.getDailyNote()).isEqualTo(scheduleSaveRequest.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(scheduleSaveRequest.getPainType());
        assertThat(result.getScheduleDate()).isEqualTo(scheduleSaveRequest.getScheduleDate());
        assertThat(result.getUserId()).isEqualTo(1L);
    }
}
