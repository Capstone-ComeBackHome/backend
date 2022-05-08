package com.comebackhome.calendar.domain.schedule.service;

import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.SimpleScheduleResponseDto;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarQueryService implements CalendarQueryUseCase{

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<SimpleScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId) {
        return scheduleRepository.findByYearMonthAndUserId(yearMonth,userId)
                .parallelStream()
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto getMySchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, userId)
                .orElseThrow(() -> new ScheduleNotFoundException());

        return ScheduleResponseDto.from(schedule);
    }

}
