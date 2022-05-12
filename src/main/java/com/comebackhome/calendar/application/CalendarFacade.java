package com.comebackhome.calendar.application;

import com.comebackhome.calendar.domain.schedule.service.CalendarCommandUseCase;
import com.comebackhome.calendar.domain.schedule.service.CalendarQueryUseCase;
import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarFacade {

    private final CalendarCommandUseCase calendarCommandUseCase;
    private final CalendarQueryUseCase calendarQueryUseCase;

    public void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        calendarCommandUseCase.saveMySchedule(scheduleSaveRequestDto);
    }

    public void deleteSchedule(Long scheduleId, Long userId) {
        calendarCommandUseCase.deleteSchedule(scheduleId, userId);
    }

    public void modifyMySchedule(Long scheduleId, Long userId, ScheduleModifyRequestDto scheduleModifyRequestDto) {
        calendarCommandUseCase.modifyMySchedule(scheduleId, userId, scheduleModifyRequestDto);
    }

    public List<ScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId) {
        return calendarQueryUseCase.getMyMonthSchedule(yearMonth, userId);
    }

    public ScheduleResponseDto getMySchedule(Long scheduleId, Long userId) {
        return calendarQueryUseCase.getMySchedule(scheduleId, userId);
    }


}
