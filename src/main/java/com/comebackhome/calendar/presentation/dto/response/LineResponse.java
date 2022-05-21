package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.calendar.domain.schedule.repository.dto.LineQueryDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LineResponse {

    private LocalDate scheduleDate;
    private PainType painType;
    private String diseaseName;

    public static LineResponse from(LineQueryDto lineQueryDto){
        return LineResponse.builder()
                .scheduleDate(lineQueryDto.getScheduleDate())
                .painType(lineQueryDto.getPainType())
                .diseaseName(lineQueryDto.getDiseaseName())
                .build();
    }
}
