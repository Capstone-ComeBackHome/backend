package com.comebackhome.calendar.infrastructure.repository.schedulediseasetag;

import com.comebackhome.calendar.domain.schedule.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.repository.dto.BubbleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ScheduleDiseaseTagRepositoryImpl implements ScheduleDiseaseTagRepository {

    private final ScheduleDiseaseTagJpaRepository scheduleDiseaseTagJpaRepository;
    private final ScheduleDiseaseTagQuerydslRepository scheduleDiseaseTagQuerydslRepository;

    @Override
    public List<Long> saveAll(List<ScheduleDiseaseTag> scheduleDiseaseTagList) {
        return scheduleDiseaseTagJpaRepository.saveAll(scheduleDiseaseTagList)
                .parallelStream()
                .map(ScheduleDiseaseTag::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByScheduleId(Long scheduleId) {
        scheduleDiseaseTagJpaRepository.deleteByScheduleId(scheduleId);
    }

    @Override
    public void deleteByIdList(List<Long> idList) {
        scheduleDiseaseTagJpaRepository.deleteByIdList(idList);
    }

    @Override
    public List<BubbleQueryDto> findBubbleQueryDtoByUserIdWithinAMonthExceptCustomType(Long userId) {
        return scheduleDiseaseTagQuerydslRepository.findBubbleQueryDtoByUserIdWithinAMonthExceptCustomType(userId);
    }


}
