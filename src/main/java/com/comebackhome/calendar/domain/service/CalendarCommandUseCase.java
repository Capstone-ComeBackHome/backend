package com.comebackhome.calendar.domain.service;


import com.comebackhome.calendar.domain.service.dto.request.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleSaveRequestDto;

public interface CalendarCommandUseCase {

    void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto);

    void deleteSchedule(Long scheduleId, Long userId);

    void modifyMySchedule(Long scheduleId, Long userId, ScheduleModifyRequestDto scheduleModifyRequestDto);
}
