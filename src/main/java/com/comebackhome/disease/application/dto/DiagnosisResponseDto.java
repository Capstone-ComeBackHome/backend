package com.comebackhome.disease.application.dto;

import com.comebackhome.disease.domain.Diagnosis;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnosisResponseDto {

    private Long diagnosisId;

    private LocalDateTime createdDate;

    private List<String> diseaseNameList;

    public static DiagnosisResponseDto of (Diagnosis diagnosis, List<String> diseaseNameList){
        return DiagnosisResponseDto.builder()
                .diagnosisId(diagnosis.getId())
                .createdDate(diagnosis.getCreatedDate())
                .diseaseNameList(diseaseNameList)
                .build();
    }
}
