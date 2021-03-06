package com.comebackhome.calendar.infrastructure.repository.schedule;

import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
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
    public boolean existsByIdAndUserId(Long id, Long userId) {
        return scheduleJpaRepository.existsByIdAndUserId(id, userId);
    }

    @Override
    public Optional<Schedule> findWithScheduleDiseaseTagByIdAndUserId(Long id, Long userId) {
        return scheduleQuerydslRepository.findWithScheduleDiseaseTagByIdAndUserId(id,userId);
    }

    @Override
    public void deleteById(Long id) {
        scheduleJpaRepository.deleteById(id);
    }

    @Override
    public List<Schedule> findWithScheduleDiseaseTagByYearMonthAndUserId(YearMonth yearMonth, Long userId) {
        return scheduleQuerydslRepository.findByYearMonthAndUserId(yearMonth,userId);
    }

}
