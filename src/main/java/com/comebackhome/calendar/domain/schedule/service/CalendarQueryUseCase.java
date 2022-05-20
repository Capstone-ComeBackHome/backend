package com.comebackhome.calendar.domain.schedule.service;


import com.comebackhome.calendar.domain.schedule.service.dto.response.BubbleResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;

import java.time.YearMonth;
import java.util.List;

public interface CalendarQueryUseCase {

    List<ScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId);

    ScheduleResponseDto getMySchedule(Long scheduleId, Long UserId);

    List<BubbleResponseDto> getBubbleStatisticData(Long userId);

}
