package com.comebackhome.calendar.domain.diseasetag.service.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class DiseaseTagListResponseDto {

    private String diseaseTypeDescription;

    private List<String> diseaseTagNameList;

    public static DiseaseTagListResponseDto of(String diseaseTypeDescription, List<DiseaseTagQueryDto> diseaseTagQueryDtoList) {
        return DiseaseTagListResponseDto.builder()
                .diseaseTypeDescription(diseaseTypeDescription)
                .diseaseTagNameList(diseaseTagQueryDtoList.stream()
                        .map(DiseaseTagQueryDto::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
