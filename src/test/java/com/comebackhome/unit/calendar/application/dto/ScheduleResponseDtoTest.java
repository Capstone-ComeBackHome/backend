package com.comebackhome.unit.calendar.application.dto;

import com.comebackhome.calendar.application.dto.ScheduleResponseDto;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.user.domain.User;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSchedule;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleResponseDtoTest {

    @Test
    void 정적_메서드_from_으로_생성_인자_diseaseTag() throws Exception{
        //given
        Schedule schedule = givenSchedule(User.builder().id(1L).build());

        //when
        ScheduleResponseDto result = ScheduleResponseDto.from(schedule);

        //then
        assertThat(result.getScheduleId()).isEqualTo(schedule.getId());
        assertThat(result.getLocalDate()).isEqualTo(schedule.getLocalDate());
        assertThat(result.getDiseaseTagDtoList().size()).isEqualTo(schedule.getScheduleDiseaseTagList().size());
        assertThat(result.getDailyNote()).isEqualTo(schedule.getDailyNote());
        assertThat(result.getPainType()).isEqualTo(schedule.getPainType());
    }
}
