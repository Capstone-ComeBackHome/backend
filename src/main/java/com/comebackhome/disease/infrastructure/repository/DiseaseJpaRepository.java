package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseJpaRepository extends JpaRepository<Disease,Long> {

}
