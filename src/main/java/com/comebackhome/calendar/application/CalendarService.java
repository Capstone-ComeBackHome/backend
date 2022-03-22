package com.comebackhome.calendar.application;

import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.ScheduleDiseaseTag;
import com.comebackhome.calendar.domain.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService implements CalendarCommandUseCase{

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;
    private final DiseaseTagRepository diseaseTagRepository;

    /**
     * CustomType인 DiseaseTag 중에서 저장되어 있지 않은 것들은 저장을 먼저 해주고
     * 모든 DiseaseTag Id값을 구해서 scheduleDiseaseTag로 만들어서 저장한다.
     * 모든 저장은 Bulk insert 처리한다.
     */
    @Override
    public void saveMySchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        Long scheduleId = scheduleRepository.save(Schedule.from(scheduleSaveRequestDto));

        List<Long> diseaseTagIdList = new ArrayList<>();
        diseaseTagIdList.addAll(getDiseaseTagIdListExceptCustomType(scheduleSaveRequestDto));

        List<String> customTypeDiseaseTagName = getCustomTypeDiseaseTagNameList(scheduleSaveRequestDto);
        if (!customTypeDiseaseTagName.isEmpty()){
            List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList
                    = diseaseTagRepository.findDiseaseTagListByDiseaseTagNameList(customTypeDiseaseTagName);
            diseaseTagIdList.addAll(getAlreadyExistCustomTypeDiseaseTagIdList(alreadyExistCustomTypeDiseaseTagList));

            List<Long> newCustomTypeDiseaseTagIdList
                    = saveNewCustomTypeDiseaseTagList(customTypeDiseaseTagName, alreadyExistCustomTypeDiseaseTagList);
            diseaseTagIdList.addAll(newCustomTypeDiseaseTagIdList);
        }

        saveDiseaseTagList(scheduleId, diseaseTagIdList);
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

    private List<Long> getAlreadyExistCustomTypeDiseaseTagIdList(List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList) {
        List<Long> alreadyExistCustomTypeDiseaseTagIdList = alreadyExistCustomTypeDiseaseTagList.parallelStream()
                .map(DiseaseTag::getId)
                .collect(Collectors.toList());
        return alreadyExistCustomTypeDiseaseTagIdList;
    }

    private List<Long> saveNewCustomTypeDiseaseTagList(List<String> customTypeDiseaseTagName, List<DiseaseTag> alreadyExistCustomTypeDiseaseTagList) {
        List<String> alreadyExistCustomTypeDiseaseTagNameList = alreadyExistCustomTypeDiseaseTagList.parallelStream()
                .map(DiseaseTag::getName)
                .collect(Collectors.toList());

        List<DiseaseTag> newCustomTypeDiseaseTagList = customTypeDiseaseTagName.parallelStream()
                .filter(name -> !alreadyExistCustomTypeDiseaseTagNameList.contains(name))
                .map(name -> DiseaseTag.of(DiseaseType.CUSTOM, name))
                .collect(Collectors.toList());

        if (newCustomTypeDiseaseTagList.isEmpty())
            return new ArrayList<>();

        return diseaseTagRepository.saveAll(newCustomTypeDiseaseTagList);
    }

    private void saveDiseaseTagList(Long scheduleId, List<Long> diseaseTagIdList) {
        List<ScheduleDiseaseTag> scheduleDiseaseTagList = diseaseTagIdList.parallelStream()
                .map(id -> ScheduleDiseaseTag.of(scheduleId, id))
                .collect(Collectors.toList());

        scheduleDiseaseTagRepository.saveAll(scheduleDiseaseTagList);
    }
}
