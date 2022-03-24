package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.application.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.domain.PainType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleResponse {

    private Long scheduleId;

    private LocalDate localDate;

    private List<DiseaseTagResponse> diseaseTagResponseList;

    private String dailyNote;

    private PainType painType;

    public static ScheduleResponse from(ScheduleResponseDto scheduleResponseDto){
        return ScheduleResponse.builder()
                .scheduleId(scheduleResponseDto.getScheduleId())
                .localDate(scheduleResponseDto.getLocalDate())
                .diseaseTagResponseList(scheduleResponseDto.getDiseaseTagDtoList().parallelStream()
                        .map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .dailyNote(scheduleResponseDto.getDailyNote())
                .painType(scheduleResponseDto.getPainType())
                .build();
    }

}
