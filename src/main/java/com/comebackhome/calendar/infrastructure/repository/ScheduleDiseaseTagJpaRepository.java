package com.comebackhome.calendar.infrastructure.repository;

import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleDiseaseTagJpaRepository extends JpaRepository<ScheduleDiseaseTag,Long> {

    @Modifying(clearAutomatically = true)
    @Query("delete from ScheduleDiseaseTag sdt where sdt.schedule.id =:id")
    void deleteByScheduleId(@Param("id") Long scheduleId);
}
