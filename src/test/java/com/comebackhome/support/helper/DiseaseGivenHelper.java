package com.comebackhome.support.helper;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.Cause;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.HomeCare;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.CauseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.HomeCareQueryDto;

import java.util.List;

public class DiseaseGivenHelper {

    public static DiseaseResponseDto givenDiseaseResponseDto(){
        return DiseaseResponseDto.from(givenDiseaseQueryDto());
    }

    public static DiseaseQueryDto givenDiseaseQueryDto(){
        return DiseaseQueryDto.of(givenDisease(),
                List.of(new CauseQueryDto("원인1"),new CauseQueryDto("원인2")),
                List.of(new HomeCareQueryDto("해결책1"),new HomeCareQueryDto("해결책2"))
                );
    }

    public static Disease givenDisease() {
        return Disease.builder()
                .name("부정맥")
                .definition("정의")
                .recommendDepartment("내과")
                .symptom("증상")
                .complications("합병증")
                .hospitalCare("병원 치료 방법")
                .build();
    }

    public static Disease givenDisease(String name) {
        return Disease.builder()
                .name(name)
                .definition("정의")
                .recommendDepartment("내과")
                .symptom("증상")
                .complications("합병증")
                .hospitalCare("병원 치료 방법")
                .build();
    }

    public static HomeCare givenHomeCare(Disease disease, String solution) {
        return HomeCare.builder()
                .disease(disease)
                .solution(solution)
                .build();
    }

    public static Cause givenCause(Disease disease, String reason) {
        return Cause.builder()
                .disease(disease)
                .reason(reason)
                .build();
    }

    public static SimpleDiseaseResponseDto givenSimpleDiseaseResponseDto(String name){
        return SimpleDiseaseResponseDto.from(givenSimpleDiseaseQueryDto(name));
    }

    public static SimpleDiseaseResponseDto givenSimpleDiseaseResponseDto(){
        return SimpleDiseaseResponseDto.from(givenSimpleDiseaseQueryDto());
    }

    public static SimpleDiseaseQueryDto givenSimpleDiseaseQueryDto(){
        return SimpleDiseaseQueryDto.builder()
                .name("부정맥")
                .definition("발작성 민백 중 하나인 부정맥은 심장 전체로 전기 신호를 전달하는 전기...")
                .recommendDepartment("내과")
                .build();
    }

    public static SimpleDiseaseQueryDto givenSimpleDiseaseQueryDto(String name){
        return SimpleDiseaseQueryDto.builder()
                .name(name)
                .definition("발작성 민백 중 하나인 부정맥은 심장 전체로 전기 신호를 전달하는 전기...")
                .recommendDepartment("내과")
                .build();
    }


}
