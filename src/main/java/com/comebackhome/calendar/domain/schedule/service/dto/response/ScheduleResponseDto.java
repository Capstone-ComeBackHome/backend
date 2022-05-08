package com.comebackhome.calendar.domain.schedule.service.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.domain.schedule.Schedule;
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

    private List<DiseaseTagResponseDto> diseaseTagResponseDtoList;

    private String dailyNote;

    private String painType;

    public static ScheduleResponseDto from(Schedule schedule){
        return ScheduleResponseDto.builder()
                .scheduleId(schedule.getId())
                .localDate(schedule.getLocalDate())
                .diseaseTagResponseDtoList(schedule.getScheduleDiseaseTagList().parallelStream()
                        .map(scheduleDiseaseTag -> DiseaseTagResponseDto.from(scheduleDiseaseTag.getDiseaseTag()))
                        .collect(Collectors.toList()))
                .dailyNote(schedule.getDailyNote())
                .painType(schedule.getPainType().name())
                .build();
    }

}
