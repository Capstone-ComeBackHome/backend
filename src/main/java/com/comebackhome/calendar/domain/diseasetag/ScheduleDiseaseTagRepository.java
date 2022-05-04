package com.comebackhome.calendar.domain.diseasetag;

import java.util.List;

public interface ScheduleDiseaseTagRepository {

    List<Long> saveAll(List<ScheduleDiseaseTag> scheduleDiseaseTagList);

    void deleteByScheduleId(Long scheduleId);

    void deleteByIdList(List<Long> idList);


}
