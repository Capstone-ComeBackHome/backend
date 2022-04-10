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

        DiagnosisResponseDto diagnosisResponseDto1 = givenDiagnosisResponseDto(1L);
        diagnosisResponseDto1.setCreatedDate(LocalDateTime.now());
        DiagnosisResponseDto diagnosisResponseDto2 = givenDiagnosisResponseDto(2L);
        diagnosisResponseDto2.setCreatedDate(LocalDateTime.now());
        DiagnosisResponseDto diagnosisResponseDto3 = givenDiagnosisResponseDto(3L);
        diagnosisResponseDto3.setCreatedDate(LocalDateTime.now());

        return DiagnosisResponseDtoList.builder()
                .diagnosisResponseDtoList(List.of(
                        diagnosisResponseDto3,
                        diagnosisResponseDto2,
                        diagnosisResponseDto1))
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
