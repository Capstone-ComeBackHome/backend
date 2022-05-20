package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.service.dto.response.BubbleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BubbleResponse {

    private DiseaseType diseaseType;

    private int count;

    private double painAverage;

    public static BubbleResponse from(BubbleResponseDto bubbleResponseDto){
        return new BubbleResponse(bubbleResponseDto.getDiseaseType(),
                bubbleResponseDto.getCount(),
                bubbleResponseDto.getPainAverage());
    }
}
