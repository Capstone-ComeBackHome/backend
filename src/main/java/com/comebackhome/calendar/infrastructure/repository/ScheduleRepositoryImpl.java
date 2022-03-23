package com.comebackhome.calendar.infrastructure.repository;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.dto.SimpleScheduleQueryDto;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final ScheduleQuerydslRepository scheduleQuerydslRepository;

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

    @Override
    public List<SimpleScheduleQueryDto> findByYearMonthAndUserId(YearMonth yearMonth, Long userId) {
        return scheduleQuerydslRepository.findByYearMonthAndUserId(yearMonth,userId);
    }
}
