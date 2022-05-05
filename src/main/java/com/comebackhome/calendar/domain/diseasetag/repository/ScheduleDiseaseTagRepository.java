package com.comebackhome.calendar.domain.diseasetag.repository;

import com.comebackhome.calendar.domain.diseasetag.ScheduleDiseaseTag;

import java.util.List;

public interface ScheduleDiseaseTagRepository {

    List<Long> saveAll(List<ScheduleDiseaseTag> scheduleDiseaseTagList);

    void deleteByScheduleId(Long scheduleId);

    void deleteByIdList(List<Long> idList);

}
