package com.comebackhome.unit.calendar.presentation.dto.response;

import com.comebackhome.calendar.presentation.dto.response.ScheduleResponse;
import com.comebackhome.calendar.presentation.dto.response.ScheduleResponseList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleResponseListTest {

    @Test
    void 정적_메서드_from_으로_생성() {
        //given
        List<ScheduleResponse> scheduleResponseList = List.of(givenScheduleResponse());

        //when
        ScheduleResponseList result = ScheduleResponseList.from(scheduleResponseList);

        //then
        assertThat(result.getScheduleResponseList().size()).isEqualTo(1);
        assertThat(result.getScheduleResponseList().get(0)).isEqualTo(scheduleResponseList.get(0));
    }
}
