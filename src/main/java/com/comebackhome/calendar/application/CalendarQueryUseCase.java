package com.comebackhome.calendar.application;


import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;

public interface CalendarQueryUseCase {

    void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto);
}
