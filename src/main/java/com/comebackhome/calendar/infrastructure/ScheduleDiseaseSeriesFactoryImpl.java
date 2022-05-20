package com.comebackhome.calendar.infrastructure;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.service.ScheduleDiseaseSeriesFactory;
import com.comebackhome.calendar.domain.schedule.service.dto.request.DiseaseTagRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleDiseaseSeriesFactoryImpl implements ScheduleDiseaseSeriesFactory {

    private final ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;
    private final DiseaseTagRepository diseaseTagRepository;

    /**
     * diseaseTagRequestDtoList 들어온 DiseaseTag 중 이미 DB에 있는 태그는 id값만 가져오고
     * DB에 없는 태그들을 DB에 저장하고 ID값을 가져온다.
     * 그리고 한번에 ScheduleId와 DiseaseTagId를 연관관계 세팅 후 ScheduleDiseaseTag를 Bulk Insert 한다.
     */
    @Override
    public void save(Long scheduleId, List<DiseaseTagRequestDto> diseaseTagRequestDtoList) {
        Set<Long> diseaseTagIdSet = getDiseaseTagIdSet(diseaseTagRequestDtoList);
        saveScheduleDiseaseTagList(scheduleId, diseaseTagIdSet);
    }

    private Set<Long> getDiseaseTagIdSet(List<DiseaseTagRequestDto> newDiseaseTagRequestDtoList) {
        java.util.Set<java.lang.Long> diseaseTagIdSet = new HashSet<>();
        diseaseTagIdSet.addAll(getDiseaseTagIdListExceptCustomType(newDiseaseTagRequestDtoList));

        List<String> customTypeDiseaseTagName = getCustomTypeDiseaseTagNameList(newDiseaseTagRequestDtoList);
        if (!customTypeDiseaseTagName.isEmpty()) {
            addCustomTypeDiseaseTagIdToSet(diseaseTagIdSet, customTypeDiseaseTagName);
        }

        return diseaseTagIdSet;
    }

    private List<Long> getDiseaseTagIdListExceptCustomType(List<DiseaseTagRequestDto> diseaseTagRequestDtoList) {
        List<String> diseaseTagNameExceptCustomType = diseaseTagRequestDtoList.parallelStream()
                .filter(diseaseTagRequestDto -> !diseaseTagRequestDto.getDiseaseType().equals(DiseaseType.CUSTOM))
                .map(DiseaseTagRequestDto::getName)
                .collect(Collectors.toList());
        return diseaseTagRepository.findDiseaseTagIdListByDiseaseTagNameList(diseaseTagNameExceptCustomType);
    }

    private List<String> getCustomTypeDiseaseTagNameList(List<DiseaseTagRequestDto> diseaseTagRequestDtoList) {
        return diseaseTagRequestDtoList.parallelStream()
                .filter(diseaseTagRequestDto -> diseaseTagRequestDto.getDiseaseType().equals(DiseaseType.CUSTOM))
                .map(DiseaseTagRequestDto::getName)
                .collect(Collectors.toList());
    }

    private void addCustomTypeDiseaseTagIdToSet(Set<Long> diseaseTagIdSet, List<String> customTypeDiseaseTagName) {
        List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList
                = diseaseTagRepository.findDiseaseTagListByDiseaseTagNameList(customTypeDiseaseTagName);
        diseaseTagIdSet.addAll(getIdList(alreadyExistCustomTypeDiseaseTagList));

        List<DiseaseTag> newCustomTypeDiseaseTagList
                = extractNewCustomTypeDiseaseTagList(customTypeDiseaseTagName, alreadyExistCustomTypeDiseaseTagList);
        List<Long> newCustomTypeDiseaseTagIdList
                = saveNewCustomTypeDiseaseTagList(newCustomTypeDiseaseTagList);
        diseaseTagIdSet.addAll(newCustomTypeDiseaseTagIdList);
    }

    private List<Long> getIdList(List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList) {
        return alreadyExistCustomTypeDiseaseTagList.parallelStream()
                .map(DiseaseTag::getId)
                .collect(Collectors.toList());
    }

    private List<DiseaseTag> extractNewCustomTypeDiseaseTagList(List<String> customTypeDiseaseTagName, List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList) {
        List<String> alreadyExistCustomTypeDiseaseTagNameList = alreadyExistCustomTypeDiseaseTagList.parallelStream()
                .map(DiseaseTag::getName)
                .collect(Collectors.toList());

        return customTypeDiseaseTagName.parallelStream()
                .filter(name -> !alreadyExistCustomTypeDiseaseTagNameList.contains(name))
                .map(name -> DiseaseTag.of(DiseaseType.CUSTOM, name))
                .collect(Collectors.toList());
    }

    private List<Long> saveNewCustomTypeDiseaseTagList(List<DiseaseTag> newCustomTypeDiseaseTagList) {
        if (newCustomTypeDiseaseTagList.isEmpty())
            return new ArrayList<>();
        return diseaseTagRepository.saveAll(newCustomTypeDiseaseTagList); // save
    }

    private void saveScheduleDiseaseTagList(Long scheduleId, Set<Long> diseaseTagIdSet) {
        if (diseaseTagIdSet.isEmpty())
            return;

        List<ScheduleDiseaseTag> scheduleDiseaseTagList = diseaseTagIdSet.parallelStream()
                .map(id -> ScheduleDiseaseTag.of(scheduleId, id))
                .collect(Collectors.toList());

        scheduleDiseaseTagRepository.saveAll(scheduleDiseaseTagList);
    }
}
