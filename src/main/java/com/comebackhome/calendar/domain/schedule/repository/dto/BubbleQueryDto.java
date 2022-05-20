package com.comebackhome.calendar.domain.schedule.repository.dto;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.PainType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BubbleQueryDto {

    private PainType painType;

    private DiseaseType diseaseType;
}
