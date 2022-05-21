package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
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

    private LocalDate scheduleDate;

    private List<DiseaseTagResponse> diseaseTagResponseList;

    private String dailyNote;

    private String painType;

    public static ScheduleResponse from(ScheduleResponseDto scheduleResponseDto){
        return ScheduleResponse.builder()
                .scheduleId(scheduleResponseDto.getScheduleId())
                .scheduleDate(scheduleResponseDto.getScheduleDate())
                .diseaseTagResponseList(scheduleResponseDto.getDiseaseTagResponseDtoList().parallelStream()
                        .map(DiseaseTagResponse::from).collect(Collectors.toList()))
                .dailyNote(scheduleResponseDto.getDailyNote())
                .painType(scheduleResponseDto.getPainType())
                .build();
    }

}
