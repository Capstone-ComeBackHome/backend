package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.HomeCare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeCareJpaRepository extends JpaRepository<HomeCare,Long> {
}
