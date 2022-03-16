package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Cause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauseJpaRepository extends JpaRepository<Cause,Long> {
}
