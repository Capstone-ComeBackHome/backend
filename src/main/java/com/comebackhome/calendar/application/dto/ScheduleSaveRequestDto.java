package com.comebackhome.calendar.application.dto;

import com.comebackhome.calendar.domain.PainType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleSaveRequestDto {

    private List<DiseaseTagRequestDto> diseaseTagRequestDtoList;

    private String dailyNote;

    private PainType painType;

    private LocalDate localDate;

    private Long userId;

}
