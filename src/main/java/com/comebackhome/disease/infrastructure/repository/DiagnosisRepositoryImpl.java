package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.domain.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    private final DiagnosisJpaRepository diagnosisJpaRepository;
    private final DiagnosisQuerydslRepository diagnosisQuerydslRepository;

    @Override
    public Long save(Diagnosis diagnosis) {
        return diagnosisJpaRepository.save(diagnosis).getId();
    }

    @Override
    public Optional<Diagnosis> findById(Long id) {
        return diagnosisJpaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        diagnosisJpaRepository.deleteById(id);
    }

    @Override
    public Slice<Diagnosis> findDiagnosisListByLastDiagnosisIdAndUserId(Long lastDiagnosisId, Long userId, Pageable pageable) {
        return diagnosisQuerydslRepository.findDiagnosisListByLastDiagnosisIdAndUserId(lastDiagnosisId, userId, pageable);

    }
}
