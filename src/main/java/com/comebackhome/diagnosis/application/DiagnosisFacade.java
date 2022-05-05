package com.comebackhome.diagnosis.application;

import com.comebackhome.diagnosis.domain.service.DiagnosisCommandUseCase;
import com.comebackhome.diagnosis.domain.service.DiagnosisQueryUseCase;
import com.comebackhome.diagnosis.domain.service.dto.DiagnosisResponseDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisFacade {

    private final DiagnosisCommandUseCase diagnosisCommandUseCase;
    private final DiagnosisQueryUseCase diagnosisQueryUseCase;

    public void createDiagnosis(List<String> diseaseNameList, Long userId) {
        diagnosisCommandUseCase.createDiagnosis(diseaseNameList,userId);
    }

    public void deleteMyDiagnosis(Long diagnosisId, Long userId) {
        diagnosisCommandUseCase.deleteMyDiagnosis(diagnosisId,userId);
    }

    public DiagnosisResponseDtoList getMyDiagnoses(Long lastDiagnosisId, Long userId, Pageable pageable) {
        return diagnosisQueryUseCase.getMyDiagnoses(lastDiagnosisId,userId,pageable);
    }

}

