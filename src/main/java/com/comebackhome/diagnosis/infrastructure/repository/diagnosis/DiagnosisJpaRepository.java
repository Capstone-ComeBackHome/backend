package com.comebackhome.diagnosis.infrastructure.repository.diagnosis;

import com.comebackhome.diagnosis.domain.diagnosis.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisJpaRepository extends JpaRepository<Diagnosis,Long> {
}
