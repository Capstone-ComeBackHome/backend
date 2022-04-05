package com.comebackhome.disease.application;

import java.util.List;

public interface DiagnosisCommandUseCase {

    void createDiagnosis(List<String> diseaseNameList, Long userId);
}
