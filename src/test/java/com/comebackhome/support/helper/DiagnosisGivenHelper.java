package com.comebackhome.support.helper;

import com.comebackhome.disease.application.dto.DiagnosisResponseDto;
import com.comebackhome.disease.application.dto.DiagnosisResponseDtoList;
import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.domain.DiagnosisDisease;
import com.comebackhome.disease.presentation.dto.DiagnosisSaveRequest;
import com.comebackhome.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public class DiagnosisGivenHelper {

    public static DiagnosisSaveRequest givenDiagnosisRequest(){
        return DiagnosisSaveRequest.builder()
                .diseaseNameList(List.of("질병1","질병2","질병3"))
                .build();
    }

    public static Diagnosis givenDiagnosis(Long userId){
        return Diagnosis.builder()
                .user(User.builder().id(userId).build())
                .build();
    }

    public static Diagnosis givenDiagnosis(){
        return Diagnosis.builder()
                .diagnosisDiseaseList(List.of(
                        DiagnosisDisease.of(1L,1L)
                ))
                .build();
    }


    public static DiagnosisResponseDtoList givenDiagnosisResponseDtoList(){
        return DiagnosisResponseDtoList.builder()
                .diagnosisResponseDtoList(List.of(
                        givenDiagnosisResponseDto(1L),
                        givenDiagnosisResponseDto(2L)))
                .hasNext(false)
                .build();
    }

    public static DiagnosisResponseDto givenDiagnosisResponseDto(Long diagnosisId){
        return DiagnosisResponseDto.builder()
                .diagnosisId(diagnosisId)
                .createdDate(LocalDateTime.now())
                .diseaseNameList(List.of("질병1","질병2","질병3"))
                .build();
    }


}
