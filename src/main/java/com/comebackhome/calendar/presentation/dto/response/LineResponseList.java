package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.service.dto.response.LineResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LineResponseList {

    private List<LineResponse> top1;
    private List<LineResponse> top2;
    private List<LineResponse> top3;
    private LocalDate before3MonthDate;


    public static LineResponseList from(LineResponseDto lineResponseDto){
        return LineResponseList.builder()
                .top1(lineResponseDto.getTop1().stream().map(LineResponse::from).collect(Collectors.toList()))
                .top2(lineResponseDto.getTop2().stream().map(LineResponse::from).collect(Collectors.toList()))
                .top3(lineResponseDto.getTop3().stream().map(LineResponse::from).collect(Collectors.toList()))
                .before3MonthDate(lineResponseDto.getBefore3MonthDate())
                .build();
    }
}
