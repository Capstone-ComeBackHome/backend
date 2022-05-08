package com.comebackhome.calendar.domain.schedule.repository;

import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.service.dto.response.SimpleScheduleResponseDto;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    boolean existsByIdAndUserId(Long id, Long userId);

    Optional<Schedule> findWithScheduleDiseaseTagByIdAndUserId(Long id,Long userId);

    List<SimpleScheduleResponseDto> findByYearMonthAndUserId(YearMonth yearMonth, Long userId);

    void deleteById(Long id);


}
