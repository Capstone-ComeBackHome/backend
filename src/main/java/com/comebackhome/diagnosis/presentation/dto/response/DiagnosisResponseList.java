package com.comebackhome.diagnosis.presentation.dto.response;

import com.comebackhome.diagnosis.domain.diagnosis.service.dto.DiagnosisResponseDtoList;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiagnosisResponseList {

    private List<DiagnosisResponse> diagnosisResponseList;

    private boolean hasNext;


    public static DiagnosisResponseList from(DiagnosisResponseDtoList diagnosisResponseDtoList){
        return DiagnosisResponseList.builder()
                .diagnosisResponseList(diagnosisResponseDtoList.getDiagnosisResponseDtoList().parallelStream()
                        .map(DiagnosisResponse::from)
                        .collect(Collectors.toList()))
                .hasNext(diagnosisResponseDtoList.hasNext())
                .build();
    }
}
