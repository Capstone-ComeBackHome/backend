package com.comebackhome.unit.calendar.domain.service.dto.request;

import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleSaveRequestDto;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleSaveRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleSaveRequestDtoTest {

    @Test
    void schedule로_전환하기() throws Exception{
        //given
        ScheduleSaveRequestDto scheduleSaveRequestDto = givenScheduleSaveRequestDto(1L);

        //when
        Schedule result = scheduleSaveRequestDto.toEntity();

        //then
        assertThat(result.getScheduleDiseaseTagList().size()).isEqualTo(result.getScheduleDiseaseTagList().size());
        assertThat(result.getUser().getId()).isEqualTo(scheduleSaveRequestDto.getUserId());
        assertThat(result.getDailyNote()).isEqualTo(scheduleSaveRequestDto.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(scheduleSaveRequestDto.getPainType());
        assertThat(result.getLocalDate()).isEqualTo(scheduleSaveRequestDto.getLocalDate());
    }
}
