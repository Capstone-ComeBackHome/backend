package com.comebackhome.calendar.domain.service;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.diseasetag.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.service.dto.request.DiseaseTagRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleSaveRequestDto;
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
public class CalendarCommandService implements CalendarCommandUseCase {

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
        Long scheduleId = scheduleRepository.save(scheduleSaveRequestDto.toEntity());

        List<DiseaseTagRequestDto> diseaseTagRequestDtoList = scheduleSaveRequestDto.getDiseaseTagRequestDtoList();
        trimDiseaseTagName(diseaseTagRequestDtoList);

        // todo 여기서 팩토리로 처리하면 조금 더 서비스를 깔끔하게 가져갈 수 있을듯
        Set<Long> diseaseTagIdSet = getDiseaseTagIdSet(diseaseTagRequestDtoList);
        saveScheduleDiseaseTagList(scheduleId, diseaseTagIdSet);
    }

    private Set<Long> getDiseaseTagIdSet(List<DiseaseTagRequestDto> newDiseaseTagRequestDtoList) {
        Set<Long> diseaseTagIdSet = new HashSet<>();
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
        return diseaseTagRepository.saveAll(newCustomTypeDiseaseTagList);
    }

    private void saveScheduleDiseaseTagList(Long scheduleId, Set<Long> diseaseTagIdSet) {
        if (diseaseTagIdSet.isEmpty())
            return;

        List<ScheduleDiseaseTag> scheduleDiseaseTagList = diseaseTagIdSet.parallelStream()
                .map(id -> ScheduleDiseaseTag.of(scheduleId, id))
                .collect(Collectors.toList());

        scheduleDiseaseTagRepository.saveAll(scheduleDiseaseTagList);
    }

    @Override
    public void deleteSchedule(Long scheduleId, Long userId) {
        checkIsMyScheduleOrThrow(scheduleId, userId);

        scheduleDiseaseTagRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }

    private void checkIsMyScheduleOrThrow(Long scheduleId, Long userId) {
        if (!scheduleRepository.existsByIdAndUserId(scheduleId, userId)) {
            throw new ScheduleNotFoundException();
        }
    }

    /**
     * scheduleModifyRequestDto의 diseaseTagRequestDto로 들어온 diseaseTag 중 DB에 저장된 diseaseTag 내용이 없는 것은 삭제
     * DB에 없는 태그들을 DB에 저장하고 ID값을 가져오고 DB에 있는 태그들은 Id값만 가져온다.
     * 그리고 한번에 ScheduleDiseaseTag를 Bulk Insert 한다.
     */
    @Override
    public void modifyMySchedule(Long scheduleId, Long userId, ScheduleModifyRequestDto scheduleModifyRequestDto) {
        Schedule schedule = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, userId)
                .orElseThrow(() -> new ScheduleNotFoundException());
        updateDailyNoteAndPainType(scheduleModifyRequestDto, schedule);

        List<DiseaseTagRequestDto> diseaseTagRequestDtoList = scheduleModifyRequestDto.getDiseaseTagRequestDtoList();
        trimDiseaseTagName(diseaseTagRequestDtoList);

        deleteScheduleDiseaseTag(schedule, diseaseTagRequestDtoList);

        Set<Long> diseaseTagIdSet = getDiseaseTagIdSet(getNewDiseaseTagRequestDtoList(schedule, diseaseTagRequestDtoList));
        saveScheduleDiseaseTagList(scheduleId, diseaseTagIdSet);
    }

    private void updateDailyNoteAndPainType(ScheduleModifyRequestDto scheduleModifyRequestDto, Schedule schedule) {
        schedule.updateDailyNote(scheduleModifyRequestDto.getDailyNote());
        schedule.updatePainType(scheduleModifyRequestDto.getPainType());
    }

    private void trimDiseaseTagName(List<DiseaseTagRequestDto> diseaseTagRequestDtoList) {
        diseaseTagRequestDtoList.forEach(diseaseTagRequestDto ->
                diseaseTagRequestDto.setName(diseaseTagRequestDto.getName().trim()));
    }

    private void deleteScheduleDiseaseTag(Schedule schedule, List<DiseaseTagRequestDto> requestDiseaseTagDtoList) {
        List<Long> haveToDeleteScheduleDiseaseTagIdList = findScheduleDiseaseTagIdListToRemove(schedule, requestDiseaseTagDtoList);
        if (haveToDeleteScheduleDiseaseTagIdList.isEmpty())
            return;
        scheduleDiseaseTagRepository.deleteByIdList(haveToDeleteScheduleDiseaseTagIdList);
    }

    private List<Long> findScheduleDiseaseTagIdListToRemove(Schedule schedule, List<DiseaseTagRequestDto> requestDiseaseTagDtoList) {
        List<String> requestDiseaseTagNameList = requestDiseaseTagDtoList.parallelStream()
                .map(DiseaseTagRequestDto::getName)
                .collect(Collectors.toList());

        return schedule.getScheduleDiseaseTagList().parallelStream()
                .filter(scheduleDiseaseTag -> !requestDiseaseTagNameList.contains(scheduleDiseaseTag.getDiseaseTag().getName()))
                .map(ScheduleDiseaseTag::getId)
                .collect(Collectors.toList());
    }

    private List<DiseaseTagRequestDto> getNewDiseaseTagRequestDtoList(Schedule schedule, List<DiseaseTagRequestDto> requestDiseaseTagDtoList) {
        List<String> existsDiseaseTagNameList
                = schedule.getScheduleDiseaseTagList().parallelStream()
                .map(scheduleDiseaseTag -> scheduleDiseaseTag.getDiseaseTag().getName())
                .collect(Collectors.toList());

        return requestDiseaseTagDtoList.parallelStream()
                .filter(diseaseTagRequestDto -> !existsDiseaseTagNameList.contains(diseaseTagRequestDto.getName()))
                .collect(Collectors.toList());
    }

}
