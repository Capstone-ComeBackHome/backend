package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.domain.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    private final DiagnosisJpaRepository diagnosisJpaRepository;

    @Override
    public Long save(Diagnosis diagnosis) {
        return diagnosisJpaRepository.save(diagnosis).getId();
    }
}
