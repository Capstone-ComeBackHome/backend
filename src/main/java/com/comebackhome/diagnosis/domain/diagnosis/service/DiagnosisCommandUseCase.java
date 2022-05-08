package com.comebackhome.diagnosis.domain.diagnosis.service;

import java.util.List;

public interface DiagnosisCommandUseCase {

    void createDiagnosis(List<String> diseaseNameList, Long userId);

    void deleteMyDiagnosis(Long diagnosisId, Long userId);
}
