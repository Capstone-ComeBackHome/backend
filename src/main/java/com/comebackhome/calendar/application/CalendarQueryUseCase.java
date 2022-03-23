package com.comebackhome.calendar.application;


import com.comebackhome.calendar.application.dto.SimpleScheduleResponseDto;

import java.time.YearMonth;
import java.util.List;

public interface CalendarQueryUseCase {

    List<SimpleScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId);
}
