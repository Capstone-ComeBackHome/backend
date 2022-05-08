package com.comebackhome.calendar.domain.schedule.service;


import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.schedule.service.dto.request.ScheduleSaveRequestDto;

public interface CalendarCommandUseCase {

    void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto);

    void deleteSchedule(Long scheduleId, Long userId);

    void modifyMySchedule(Long scheduleId, Long userId, ScheduleModifyRequestDto scheduleModifyRequestDto);
}
