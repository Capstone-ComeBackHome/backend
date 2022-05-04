package com.comebackhome.calendar.domain.service.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleScheduleResponseDto {

    private Long scheduleId;

    private LocalDate localDate;

    private int diseaseTagCount;
}
