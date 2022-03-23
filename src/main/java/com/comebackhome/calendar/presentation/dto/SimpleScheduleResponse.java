package com.comebackhome.calendar.presentation.dto;


import com.comebackhome.calendar.application.dto.SimpleScheduleResponseDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleScheduleResponse {

    private Long scheduleId;

    private LocalDate localDate;

    private int diseaseTagCount;

    public static SimpleScheduleResponse from(SimpleScheduleResponseDto simpleScheduleResponseDto){
        return SimpleScheduleResponse.builder()
                .scheduleId(simpleScheduleResponseDto.getScheduleId())
                .localDate(simpleScheduleResponseDto.getLocalDate())
                .diseaseTagCount(simpleScheduleResponseDto.getDiseaseTagCount())
                .build();
    }
}
