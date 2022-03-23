package com.comebackhome.calendar.domain.repository;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.dto.SimpleScheduleQueryDto;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    Optional<Schedule> findById(Long id);

    Optional<Schedule> findWithScheduleDiseaseTagByIdAndUserId(Long id,Long userId);

    List<SimpleScheduleQueryDto> findByYearMonthAndUserId(YearMonth yearMonth, Long userId);

    void deleteById(Long id);


}
