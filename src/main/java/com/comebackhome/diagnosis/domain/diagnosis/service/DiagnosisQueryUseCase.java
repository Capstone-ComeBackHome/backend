package com.comebackhome.diagnosis.domain.diagnosis.service;

import com.comebackhome.diagnosis.domain.diagnosis.service.dto.DiagnosisResponseDtoList;
import org.springframework.data.domain.Pageable;

public interface DiagnosisQueryUseCase {

    DiagnosisResponseDtoList getMyDiagnoses(Long lastDiagnosisId, Long userId, Pageable pageable);
}
