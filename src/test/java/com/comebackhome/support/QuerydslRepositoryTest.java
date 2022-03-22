package com.comebackhome.support;

import com.comebackhome.calendar.infrastructure.repository.DiseaseTagQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.DiseaseTagRepositoryImpl;
import com.comebackhome.config.QuerydslConfig;
import com.comebackhome.disease.infrastructure.repository.CauseQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseRepositoryImpl;
import com.comebackhome.disease.infrastructure.repository.HomeCareQuerydslRepository;
import org.springframework.context.annotation.Import;

@Import({
        QuerydslConfig.class,
        HomeCareQuerydslRepository.class,
        CauseQuerydslRepository.class,
        DiseaseQuerydslRepository.class,
        DiseaseRepositoryImpl.class,
        DiseaseTagQuerydslRepository.class,
        DiseaseTagRepositoryImpl.class

})
public abstract class QuerydslRepositoryTest extends JpaRepositoryTest{
}
