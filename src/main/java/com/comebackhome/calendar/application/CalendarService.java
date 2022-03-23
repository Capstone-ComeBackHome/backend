package com.comebackhome.calendar.application;

import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import com.comebackhome.common.exception.schedule.ScheduleIsNotMineException;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService implements CalendarCommandUseCase{

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;
    private final DiseaseTagRepository diseaseTagRepository;

    /**
     * scheduleSaveRequestDto로 들어온 DiseaseTag 중 이미 DB에 있는 태그는 id값만 가져오고
     * DB에 없는 태그들을 DB에 저장하고 ID값을 가져온다.
     * 그리고 한번에 ScheduleDiseaseTag를 Bulk Insert 한다.
     */
    @Override
    public void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        Long scheduleId = scheduleRepository.save(Schedule.from(scheduleSaveRequestDto));

        Set<Long> diseaseTagIdSet = new HashSet<>();
        diseaseTagIdSet.addAll(getDiseaseTagIdListExceptCustomType(scheduleSaveRequestDto));

        List<String> customTypeDiseaseTagName = getCustomTypeDiseaseTagNameList(scheduleSaveRequestDto);
        if (!customTypeDiseaseTagName.isEmpty()){
            List<DiseaseTag> alreadyExistDiseaseTagList
                    = diseaseTagRepository.findDiseaseTagListByDiseaseTagNameList(customTypeDiseaseTagName);
            diseaseTagIdSet.addAll(getIdList(alreadyExistDiseaseTagList));

            List<DiseaseTag> newCustomTypeDiseaseTagList
                    = extractNewCustomTypeDiseaseTagList(customTypeDiseaseTagName, alreadyExistDiseaseTagList);
            List<Long> newCustomTypeDiseaseTagIdList
                    = saveNewCustomTypeDiseaseTagList(newCustomTypeDiseaseTagList);
            diseaseTagIdSet.addAll(newCustomTypeDiseaseTagIdList);
        }

        saveScheduleDiseaseTagList(scheduleId, diseaseTagIdSet);
    }

    private List<Long> getDiseaseTagIdListExceptCustomType(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        List<String> diseaseTagNameExceptCustomType = scheduleSaveRequestDto.getDiseaseTagRequestDtoList().parallelStream()
                .filter(diseaseTagRequestDto -> !diseaseTagRequestDto.getDiseaseType().equals(DiseaseType.CUSTOM))
                .map(diseaseTagRequestDto -> diseaseTagRequestDto.getName().trim())
                .collect(Collectors.toList());
        return diseaseTagRepository.findDiseaseTagIdListByDiseaseTagNameList(diseaseTagNameExceptCustomType);
    }

    private List<String> getCustomTypeDiseaseTagNameList(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        return scheduleSaveRequestDto.getDiseaseTagRequestDtoList().parallelStream()
                .filter(diseaseTagRequestDto -> diseaseTagRequestDto.getDiseaseType().equals(DiseaseType.CUSTOM))
                .map(diseaseTagRequestDto -> diseaseTagRequestDto.getName().trim())
                .collect(Collectors.toList());
    }

    private List<Long> getIdList(List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList) {
        List<Long> alreadyExistCustomTypeDiseaseTagIdList = alreadyExistCustomTypeDiseaseTagList.parallelStream()
                .map(DiseaseTag::getId)
                .collect(Collectors.toList());
        return alreadyExistCustomTypeDiseaseTagIdList;
    }

    private List<DiseaseTag> extractNewCustomTypeDiseaseTagList(List<String> customTypeDiseaseTagName, List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList) {
        List<String> alreadyExistCustomTypeDiseaseTagNameList = alreadyExistCustomTypeDiseaseTagList.parallelStream()
                .map(DiseaseTag::getName)
                .collect(Collectors.toList());

        List<DiseaseTag> newCustomTypeDiseaseTagList = customTypeDiseaseTagName.parallelStream()
                .filter(name -> !alreadyExistCustomTypeDiseaseTagNameList.contains(name))
                .map(name -> DiseaseTag.of(DiseaseType.CUSTOM, name))
                .collect(Collectors.toList());
        return newCustomTypeDiseaseTagList;
    }

    private List<Long> saveNewCustomTypeDiseaseTagList(List<DiseaseTag> newCustomTypeDiseaseTagList) {


        if (newCustomTypeDiseaseTagList.isEmpty())
            return new ArrayList<>();

        return diseaseTagRepository.saveAll(newCustomTypeDiseaseTagList);
    }

    private void saveScheduleDiseaseTagList(Long scheduleId, Set<Long> diseaseTagIdSet) {
        List<ScheduleDiseaseTag> scheduleDiseaseTagList = diseaseTagIdSet.parallelStream()
                .map(id -> ScheduleDiseaseTag.of(scheduleId, id))
                .collect(Collectors.toList());

        scheduleDiseaseTagRepository.saveAll(scheduleDiseaseTagList);
    }


    @Override
    public void deleteSchedule(Long scheduleId, Long UserId) {
        checkIsMyScheduleOrThrow(scheduleId, UserId);

        scheduleDiseaseTagRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    private void checkIsMyScheduleOrThrow(Long scheduleId, Long UserId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException());

        if (!schedule.getUser().getId().equals(UserId)){
            throw new ScheduleIsNotMineException();
        }
    }
}
