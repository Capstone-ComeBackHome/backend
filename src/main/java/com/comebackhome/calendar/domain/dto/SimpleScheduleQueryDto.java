package com.comebackhome.calendar.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleScheduleQueryDto {

    private Long scheduleId;

    private LocalDate localDate;

    private int diseaseTagCount;
}
