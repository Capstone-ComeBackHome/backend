package com.comebackhome.calendar.domain.repository;

import com.comebackhome.calendar.domain.Schedule;

import java.util.Optional;

public interface ScheduleRepository {

    Long save(Schedule schedule);

    Optional<Schedule> findById(Long id);

    void deleteById(Long id);

}
