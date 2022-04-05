package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.domain.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    private final DiagnosisJpaRepository diagnosisJpaRepository;

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
}
