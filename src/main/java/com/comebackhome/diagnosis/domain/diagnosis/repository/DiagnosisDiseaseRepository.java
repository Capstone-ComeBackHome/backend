package com.comebackhome.diagnosis.domain.diagnosis.repository;

import com.comebackhome.diagnosis.domain.diagnosis.DiagnosisDisease;

import java.util.List;

public interface DiagnosisDiseaseRepository {

    void saveAll(List<DiagnosisDisease> diagnosisDiseaseList);

    void deleteByDiagnosisId(Long diagnosisId);
}
