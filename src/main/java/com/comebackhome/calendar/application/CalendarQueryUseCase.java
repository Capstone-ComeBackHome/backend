package com.comebackhome.calendar.application;


import com.comebackhome.calendar.application.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.application.dto.response.SimpleScheduleResponseDto;

import java.time.YearMonth;
import java.util.List;

public interface CalendarQueryUseCase {

    List<SimpleScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId);

    ScheduleResponseDto getMySchedule(Long scheduleId, Long UserId);

}
