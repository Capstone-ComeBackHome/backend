package com.comebackhome.calendar.domain.schedule.repository.dto;

import com.comebackhome.calendar.domain.schedule.PainType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineQueryDto {
    private LocalDate scheduleDate;
    private PainType painType;
    private String diseaseName;
}
