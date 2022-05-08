package com.comebackhome.diagnosis.domain.diagnosis.service.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnosisResponseDtoList {

    private List<DiagnosisResponseDto> diagnosisResponseDtoList;

    private boolean hasNext;

    public List<DiagnosisResponseDto> getDiagnosisResponseDtoList() {
        return diagnosisResponseDtoList;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public static DiagnosisResponseDtoList of(List<DiagnosisResponseDto> diagnosisResponseDtoList, boolean hasNext){
        return DiagnosisResponseDtoList.builder()
                .diagnosisResponseDtoList(diagnosisResponseDtoList)
                .hasNext(hasNext)
                .build();
    }
}
