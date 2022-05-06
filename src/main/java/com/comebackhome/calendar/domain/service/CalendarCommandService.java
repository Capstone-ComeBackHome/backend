package com.comebackhome.calendar.domain.service;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.diseasetag.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.service.dto.request.DiseaseTagRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleModifyRequestDto;
import com.comebackhome.calendar.domain.service.dto.request.ScheduleSaveRequestDto;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarCommandService implements CalendarCommandUseCase {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;
    private final ScheduleDiseaseSeriesFactory scheduleDiseaseSeriesFactory;

    /**
     * schedule을 저장한다.
     * DB에 없는 diseaseTag는 저장하고 scheduleId와 diseaseTagId를 ScheduleDisease에 연관관계 세팅 후 Bulk Insert 한다.
     */
    @Override
    public void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        Long scheduleId = scheduleRepository.save(scheduleSaveRequestDto.toEntity());
        List<DiseaseTagRequestDto> diseaseTagRequestDtoList = scheduleSaveRequestDto.getDiseaseTagRequestDtoList();
        trimDiseaseTagName(diseaseTagRequestDtoList);
        scheduleDiseaseSeriesFactory.save(scheduleId, diseaseTagRequestDtoList);
    }

    private void trimDiseaseTagName(List<DiseaseTagRequestDto> diseaseTagRequestDtoList) {
        diseaseTagRequestDtoList.forEach(diseaseTagRequestDto ->
                diseaseTagRequestDto.setName(diseaseTagRequestDto.getName().trim()));
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
        scheduleDiseaseSeriesFactory.save(scheduleId,getNewDiseaseTagRequestDtoList(schedule, diseaseTagRequestDtoList));
    }

    private void updateDailyNoteAndPainType(ScheduleModifyRequestDto scheduleModifyRequestDto, Schedule schedule) {
        schedule.updateDailyNote(scheduleModifyRequestDto.getDailyNote());
        schedule.updatePainType(scheduleModifyRequestDto.getPainType());
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
