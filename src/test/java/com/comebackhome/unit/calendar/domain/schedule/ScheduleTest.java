package com.comebackhome.unit.calendar.domain.schedule;

import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.user.domain.User;
import org.junit.jupiter.api.Test;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenSchedule;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleTest {

    @Test
    void painType_업데이트() throws Exception{
        //given
        Schedule schedule = givenSchedule(User.builder().id(1L).build());

        //when
        schedule.updatePainType(PainType.BAD);

        //then
        assertThat(schedule.getPainType()).isEqualTo(PainType.BAD);
    }

    @Test
    void dailyNote_업데이트() throws Exception{
        //given
        Schedule schedule = givenSchedule(User.builder().id(1L).build());
        String dailyNote = "오늘은 피곤해요.";

        //when
        schedule.updateDailyNote(dailyNote);

        //then
        assertThat(schedule.getDailyNote()).isEqualTo(dailyNote);
    }
}
