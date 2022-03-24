package com.comebackhome.calendar.application.dto.response;

import com.comebackhome.calendar.domain.PainType;
import com.comebackhome.calendar.domain.Schedule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleResponseDto {

    private Long scheduleId;

    private LocalDate localDate;

    private List<DiseaseTagDto> diseaseTagDtoList;

    private String dailyNote;

    private PainType painType;

    public static ScheduleResponseDto from(Schedule schedule){
        return ScheduleResponseDto.builder()
                .scheduleId(schedule.getId())
                .localDate(schedule.getLocalDate())
                .diseaseTagDtoList(schedule.getScheduleDiseaseTagList().parallelStream()
                        .map(scheduleDiseaseTag -> DiseaseTagDto.from(scheduleDiseaseTag.getDiseaseTag()))
                        .collect(Collectors.toList()))
                .dailyNote(schedule.getDailyNote())
                .painType(schedule.getPainType())
                .build();
    }

}
