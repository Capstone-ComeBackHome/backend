package com.comebackhome.disease.presentation.dto;

import com.comebackhome.disease.application.dto.DiagnosisResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnosisResponse {

    private Long diagnosisId;

    private LocalDateTime createdDate;

    private List<String> diseaseNameList;

    public static DiagnosisResponse from(DiagnosisResponseDto diagnosisResponseDto){
        return DiagnosisResponse.builder()
                .diagnosisId(diagnosisResponseDto.getDiagnosisId())
                .createdDate(diagnosisResponseDto.getCreatedDate())
                .diseaseNameList(diagnosisResponseDto.getDiseaseNameList())
                .build();
    }
}
