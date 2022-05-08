package com.comebackhome.diagnosis.infrastructure.repository.diagnosisdisease;


import com.comebackhome.diagnosis.domain.diagnosis.DiagnosisDisease;
import com.comebackhome.diagnosis.domain.diagnosis.repository.DiagnosisDiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiagnosisDiseaseRepositoryImpl implements DiagnosisDiseaseRepository {


    private final DiagnosisDiseaseJdbcRepository diagnosisDiseaseJdbcRepository;
    private final DiagnosisDiseaseJpaRepository diagnosisDiseaseJpaRepository;

    @Override
    public void saveAll(List<DiagnosisDisease> diagnosisDiseaseList) {
        diagnosisDiseaseJdbcRepository.saveAll(diagnosisDiseaseList);
    }

    @Override
    public void deleteByDiagnosisId(Long diagnosisId) {
        diagnosisDiseaseJpaRepository.deleteByDiagnosisId(diagnosisId);
    }
}
