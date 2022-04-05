package com.comebackhome.disease.domain;

import java.util.Optional;

public interface DiagnosisRepository {

    Long save(Diagnosis diagnosis);

    Optional<Diagnosis> findById(Long id);

    void deleteById(Long id);
}
