package com.comebackhome.calendar.infrastructure.repository;

import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.repository.ScheduleDiseaseTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ScheduleDiseaseTagRepositoryImpl implements ScheduleDiseaseTagRepository {

    private final ScheduleDiseaseTagJpaRepository scheduleDiseaseTagJpaRepository;

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


}
