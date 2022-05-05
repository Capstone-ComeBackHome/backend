package com.comebackhome.diagnosis.domain.service;

import com.comebackhome.diagnosis.domain.service.dto.DiagnosisResponseDtoList;
import org.springframework.data.domain.Pageable;

public interface DiagnosisQueryUseCase {

    DiagnosisResponseDtoList getMyDiagnoses(Long lastDiagnosisId, Long userId, Pageable pageable);
}
