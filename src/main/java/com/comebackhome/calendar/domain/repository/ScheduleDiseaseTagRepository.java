package com.comebackhome.calendar.domain.repository;

import com.comebackhome.calendar.domain.ScheduleDiseaseTag;

import java.util.List;

public interface ScheduleDiseaseTagRepository {

    List<Long> saveAll(List<ScheduleDiseaseTag> scheduleDiseaseTagList);

    // todo id는 스케줄 아이디라서 이름 바꾸는게 나을듯
    void deleteByScheduleId(Long scheduleId);

    void deleteByIdList(List<Long> idList);


}
