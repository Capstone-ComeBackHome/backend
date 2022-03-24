package com.comebackhome.calendar.application.dto.request;

import com.comebackhome.calendar.domain.PainType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleModifyRequestDto {

    private List<DiseaseTagRequestDto> diseaseTagRequestDtoList;

    private String dailyNote;

    private PainType painType;

}
