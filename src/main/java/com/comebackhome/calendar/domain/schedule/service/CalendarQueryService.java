package com.comebackhome.calendar.domain.schedule.service;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.Schedule;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleDiseaseTagRepository;
import com.comebackhome.calendar.domain.schedule.repository.ScheduleRepository;
import com.comebackhome.calendar.domain.schedule.repository.dto.BubbleQueryDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.BubbleResponseDto;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarQueryService implements CalendarQueryUseCase{

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDiseaseTagRepository scheduleDiseaseTagRepository;

    @Override
    public List<ScheduleResponseDto> getMyMonthSchedule(YearMonth yearMonth, Long userId) {
        return scheduleRepository.findWithScheduleDiseaseTagByYearMonthAndUserId(yearMonth, userId)
                .parallelStream()
                .map(ScheduleResponseDto::from)
                .sorted(Comparator.comparing(ScheduleResponseDto::getLocalDate))
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto getMySchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findWithScheduleDiseaseTagByIdAndUserId(scheduleId, userId)
                .orElseThrow(() -> new ScheduleNotFoundException());

        return ScheduleResponseDto.from(schedule);
    }


    // 6개 카테고리 구분해아한다.
    @Override
    public List<BubbleResponseDto> getBubbleStatisticData(Long userId) {
        List<BubbleQueryDto> bubbleQueryDtoList = scheduleDiseaseTagRepository.findBubbleQueryDtoByUserId(userId);

        List<BubbleResponseDto> bubbleResponseDtoList = new ArrayList<>();
        addBubbleResponseDtoGroupByDiseaseType(bubbleQueryDtoList, bubbleResponseDtoList);

        return bubbleResponseDtoList;
    }

    private void addBubbleResponseDtoGroupByDiseaseType(List<BubbleQueryDto> bubbleQueryDtoList,
                                                        List<BubbleResponseDto> bubbleResponseDtoList) {

        for (DiseaseType diseaseType : DiseaseType.values()) {
            if (diseaseType.equals(DiseaseType.CUSTOM))
                continue;
            List<BubbleQueryDto> filteredBubbleQueryDto = bubbleQueryDtoList.stream()
                    .filter(bubble -> bubble.getDiseaseType().equals(diseaseType))
                    .collect(Collectors.toList());

            bubbleResponseDtoList.add(new BubbleResponseDto(diseaseType,
                    filteredBubbleQueryDto.size(),
                    calculatePainAvg(filteredBubbleQueryDto)));
        }
    }

    private double calculatePainAvg(List<BubbleQueryDto> filteredBubbleQueryDto) {
        if (filteredBubbleQueryDto.size() == 0)
            return 0;

        int sum = 0;
        for (BubbleQueryDto bubbleQueryDto : filteredBubbleQueryDto) {
            sum += bubbleQueryDto.getPainType().getNum();
        }
        return (double)sum / filteredBubbleQueryDto.size();
    }

}
