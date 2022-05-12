package com.comebackhome.calendar.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ScheduleResponseList {

    private List<ScheduleResponse> scheduleResponseList;

    public static ScheduleResponseList from (List<ScheduleResponse> scheduleResponseList){
        return ScheduleResponseList.builder()
                .scheduleResponseList(scheduleResponseList)
                .build();
    }
}
