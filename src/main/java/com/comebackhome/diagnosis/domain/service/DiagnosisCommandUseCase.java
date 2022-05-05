package com.comebackhome.diagnosis.domain.service;

import java.util.List;

public interface DiagnosisCommandUseCase {

    void createDiagnosis(List<String> diseaseNameList, Long userId);

    void deleteMyDiagnosis(Long diagnosisId, Long userId);
}
