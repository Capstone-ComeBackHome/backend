package com.comebackhome.calendar.domain.schedule.repository;

import com.comebackhome.calendar.domain.schedule.Schedule;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    boolean existsByIdAndUserId(Long id, Long userId);

    Optional<Schedule> findWithScheduleDiseaseTagByIdAndUserId(Long id,Long userId);

    List<Schedule> findWithScheduleDiseaseTagByYearMonthAndUserId(YearMonth yearMonth, Long userId);

    void deleteById(Long id);


}
