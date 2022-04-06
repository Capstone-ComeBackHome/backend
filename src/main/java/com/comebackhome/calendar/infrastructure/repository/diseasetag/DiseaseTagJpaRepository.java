package com.comebackhome.calendar.infrastructure.repository.diseasetag;

import com.comebackhome.calendar.domain.DiseaseTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseTagJpaRepository extends JpaRepository<DiseaseTag,Long> {
}
