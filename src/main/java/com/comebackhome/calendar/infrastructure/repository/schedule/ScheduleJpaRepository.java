package com.comebackhome.calendar.infrastructure.repository.schedule;

import com.comebackhome.calendar.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<Schedule,Long> {

    boolean existsByIdAndUserId(Long id, Long userId);
}
