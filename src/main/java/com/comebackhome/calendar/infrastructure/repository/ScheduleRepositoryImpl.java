package com.comebackhome.calendar.infrastructure.repository;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;

    @Override
    public Long save(Schedule schedule) {
        return scheduleJpaRepository.save(schedule).getId();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        scheduleJpaRepository.deleteById(id);
    }
}
