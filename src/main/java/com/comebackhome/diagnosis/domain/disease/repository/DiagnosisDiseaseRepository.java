package com.comebackhome.diagnosis.domain.disease.repository;

import com.comebackhome.diagnosis.domain.disease.DiagnosisDisease;

import java.util.List;

public interface DiagnosisDiseaseRepository {

    void saveAll(List<DiagnosisDisease> diagnosisDiseaseList);

    void deleteByDiagnosisId(Long diagnosisId);
}
