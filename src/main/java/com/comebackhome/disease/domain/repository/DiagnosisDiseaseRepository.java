package com.comebackhome.disease.domain.repository;

import com.comebackhome.disease.domain.DiagnosisDisease;

import java.util.List;

public interface DiagnosisDiseaseRepository {

    void saveAll(List<DiagnosisDisease> diagnosisDiseaseList);

    void deleteByDiagnosisId(Long diagnosisId);
}
