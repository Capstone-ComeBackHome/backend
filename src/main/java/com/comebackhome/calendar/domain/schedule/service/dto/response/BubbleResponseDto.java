package com.comebackhome.calendar.domain.schedule.service.dto.response;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BubbleResponseDto {

    private DiseaseType diseaseType;

    private int count;

    private double painAverage;
}
