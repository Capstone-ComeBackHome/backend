package com.comebackhome.calendar.application.dto.response;


import com.comebackhome.calendar.domain.dto.SimpleScheduleQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleScheduleResponseDto {

    private Long scheduleId;

    private LocalDate localDate;

    private int diseaseTagCount;

    public static SimpleScheduleResponseDto from (SimpleScheduleQueryDto simpleScheduleQueryDto){
        return SimpleScheduleResponseDto.builder()
                .scheduleId(simpleScheduleQueryDto.getScheduleId())
                .localDate(simpleScheduleQueryDto.getLocalDate())
                .diseaseTagCount(simpleScheduleQueryDto.getDiseaseTagCount())
                .build();
    }
}
