package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.schedule.service.dto.response.SimpleScheduleResponseDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleScheduleResponseList {

    List<SimpleScheduleResponse> simpleScheduleResponseList;

    public static SimpleScheduleResponseList from(List<SimpleScheduleResponseDto> simpleScheduleResponseDtoList){
        return SimpleScheduleResponseList.builder()
                .simpleScheduleResponseList(simpleScheduleResponseDtoList.parallelStream()
                        .map(SimpleScheduleResponse::from).collect(Collectors.toList()))
                .build();
    }

}
