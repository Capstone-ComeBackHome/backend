package com.comebackhome.calendar.domain.repository;

import com.comebackhome.calendar.domain.ScheduleDiseaseTag;

import java.util.List;

public interface ScheduleDiseaseTagRepository {

    List<Long> saveAll(List<ScheduleDiseaseTag> scheduleDiseaseTagList);
}
