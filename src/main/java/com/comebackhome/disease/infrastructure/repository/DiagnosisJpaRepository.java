package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis,Long> {
}
