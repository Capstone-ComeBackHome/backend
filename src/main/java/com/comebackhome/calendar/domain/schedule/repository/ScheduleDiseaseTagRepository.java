package com.comebackhome.calendar.domain.schedule.repository;

import com.comebackhome.calendar.domain.schedule.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.schedule.repository.dto.BubbleQueryDto;
import com.comebackhome.calendar.domain.schedule.repository.dto.LineQueryDto;

import java.util.List;

public interface ScheduleDiseaseTagRepository {

    List<Long> saveAll(List<ScheduleDiseaseTag> scheduleDiseaseTagList);

    void deleteByScheduleId(Long scheduleId);

    void deleteByIdList(List<Long> idList);

    List<BubbleQueryDto> findBubbleQueryDtoByUserIdWithinAMonthExceptCustomType(Long userId);

    List<LineQueryDto> findLineQueryDtoByUserIdWithinThreeMonthExceptCustomType(Long userId);

}
