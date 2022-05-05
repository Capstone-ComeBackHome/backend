package com.comebackhome.diagnosis.domain.service.dto;

import com.comebackhome.diagnosis.domain.Diagnosis;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
