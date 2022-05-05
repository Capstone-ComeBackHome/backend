package com.comebackhome.calendar.presentation.dto.response;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiseaseTagListResponse {

    private String diseaseTypeDescription;

    private List<String> diseaseTagNameList;

    public static DiseaseTagListResponse from(DiseaseTagListResponseDto diseaseTagListResponseDto){
        return DiseaseTagListResponse.builder()
                .diseaseTypeDescription(diseaseTagListResponseDto.getDiseaseTypeDescription())
                .diseaseTagNameList(diseaseTagListResponseDto.getDiseaseTagNameList())
                .build();
    }
}
