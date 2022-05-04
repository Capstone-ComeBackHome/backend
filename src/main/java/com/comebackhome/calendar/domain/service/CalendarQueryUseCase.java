package com.comebackhome.calendar.domain.service;


import com.comebackhome.calendar.domain.service.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.domain.service.dto.response.SimpleScheduleResponseDto;

import java.time.YearMonth;
import java.util.List;

public interface CalendarQueryUseCase {

    List<SimpleScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId);

    ScheduleResponseDto getMySchedule(Long scheduleId, Long UserId);

}
