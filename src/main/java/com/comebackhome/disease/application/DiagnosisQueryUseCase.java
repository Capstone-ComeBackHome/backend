package com.comebackhome.disease.application;

import com.comebackhome.disease.application.dto.DiagnosisResponseDtoList;
import org.springframework.data.domain.Pageable;

public interface DiagnosisQueryUseCase {

    DiagnosisResponseDtoList getMyDiagnoses(Long lastDiagnosisId, Long userId, Pageable pageable);
}
