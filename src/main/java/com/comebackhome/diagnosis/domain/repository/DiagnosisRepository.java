package com.comebackhome.diagnosis.domain.repository;

import com.comebackhome.diagnosis.domain.Diagnosis;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface DiagnosisRepository {

    Long save(Diagnosis diagnosis);

    Optional<Diagnosis> findById(Long id);

    void deleteById(Long id);

    Slice<Diagnosis> findDiagnosisListByLastDiagnosisIdAndUserId(Long lastDiagnosisId, Long userId, Pageable pageable);
}
