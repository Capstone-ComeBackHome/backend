package com.comebackhome.calendar.application;

import com.comebackhome.calendar.application.dto.SimpleScheduleResponseDto;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
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
        return scheduleRepository.findByYearMonthAndUserId(yearMonth,userId).parallelStream()
                .map(SimpleScheduleResponseDto::from)
                .collect(Collectors.toList());
    }
}
