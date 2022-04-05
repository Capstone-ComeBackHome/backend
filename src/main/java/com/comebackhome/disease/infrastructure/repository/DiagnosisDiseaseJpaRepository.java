package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.DiagnosisDisease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisDiseaseJpaRepository extends JpaRepository<DiagnosisDisease,Long> {
}
