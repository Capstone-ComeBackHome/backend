package com.comebackhome.calendar.infrastructure.repository;

import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDiseaseTagJpaRepository extends JpaRepository<ScheduleDiseaseTag,Long> {
}
