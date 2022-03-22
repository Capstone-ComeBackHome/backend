package com.comebackhome.support.helper;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;

public class DiseaseGivenHelper {

    public static DiseaseResponseDto givenDiseaseResponseDto(){
        return DiseaseResponseDto.from(givenDisease());
    }


    public static Disease givenDisease() {
        return Disease.builder()
                .name("부정맥")
                .definition("정의")
                .recommendDepartment("내과")
                .symptom("증상")
                .cause("원인")
                .hospitalCare("병원 치료 방법")
                .build();
    }

    public static Disease givenDisease(String name) {
        return Disease.builder()
                .name(name)
                .definition("정의")
                .recommendDepartment("내과")
                .symptom("증상")
                .cause("원인")
                .hospitalCare("병원 치료 방법")
                .build();
    }


    public static SimpleDiseaseResponseDto givenSimpleDiseaseResponseDto(String name, Long diseaseId){
        return SimpleDiseaseResponseDto.from(givenSimpleDiseaseQueryDto(name, diseaseId));
    }

    public static SimpleDiseaseQueryDto givenSimpleDiseaseQueryDto(String name, Long diseaseId){
        return SimpleDiseaseQueryDto.builder()
                .diseaseId(diseaseId)
                .name(name)
                .definition("발작성 민백 중 하나인 부정맥은 심장 전체로 전기 신호를 전달하는 전기...")
                .recommendDepartment("내과")
                .build();
    }


    public static SimpleDiseaseResponseDto givenSimpleDiseaseResponseDto(){
        return SimpleDiseaseResponseDto.from(givenSimpleDiseaseQueryDto());
    }

    public static SimpleDiseaseQueryDto givenSimpleDiseaseQueryDto(){
        return SimpleDiseaseQueryDto.builder()
                .diseaseId(1L)
                .name("부정맥")
                .definition("발작성 민백 중 하나인 부정맥은 심장 전체로 전기 신호를 전달하는 전기...")
                .recommendDepartment("내과")
                .build();
    }


}
