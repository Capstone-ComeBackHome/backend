package com.comebackhome.diagnosis.domain.diagnosis.repository;

import com.comebackhome.diagnosis.domain.diagnosis.Diagnosis;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface DiagnosisRepository {

    Long save(Diagnosis diagnosis);

    Optional<Diagnosis> findById(Long id);

    void deleteById(Long id);

    Slice<Diagnosis> findDiagnosisListByLastDiagnosisIdAndUserId(Long lastDiagnosisId, Long userId, Pageable pageable);
}
