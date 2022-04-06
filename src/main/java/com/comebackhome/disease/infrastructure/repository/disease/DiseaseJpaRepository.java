package com.comebackhome.disease.infrastructure.repository.disease;

import com.comebackhome.disease.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiseaseJpaRepository extends JpaRepository<Disease,Long> {

    @Query("select d.id from Disease d where d.name =:name")
    Optional<Long> findIdByName(@Param("name") String name);
}
