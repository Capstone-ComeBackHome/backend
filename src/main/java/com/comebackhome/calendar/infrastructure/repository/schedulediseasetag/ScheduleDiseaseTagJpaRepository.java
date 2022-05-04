package com.comebackhome.calendar.infrastructure.repository.schedulediseasetag;

import com.comebackhome.calendar.domain.diseasetag.ScheduleDiseaseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleDiseaseTagJpaRepository extends JpaRepository<ScheduleDiseaseTag,Long> {

    @Modifying(clearAutomatically = true)
    @Query("delete from ScheduleDiseaseTag sdt where sdt.schedule.id =:id")
    void deleteByScheduleId(@Param("id") Long scheduleId);

    @Modifying(clearAutomatically = true)
    @Query("delete from ScheduleDiseaseTag sdt where sdt.id in :ids")
    void deleteByIdList(@Param("ids") List<Long> idList);
}
