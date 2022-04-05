package com.comebackhome.support.helper;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.presentation.dto.DiagnosisRequest;
import com.comebackhome.user.domain.User;

import java.util.List;

public class DiagnosisGivenHelper {

    public static DiagnosisRequest givenDiagnosisRequest(){
        return DiagnosisRequest.builder()
                .diseaseNameList(List.of("질병1","질병2","질병3"))
                .build();
    }

    public static Diagnosis givenDiagnosis(Long userId){
        return Diagnosis.builder()
                .user(User.builder().id(userId).build())
                .build();
    }


}
