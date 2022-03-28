package com.comebackhome.support;

import com.comebackhome.calendar.infrastructure.repository.DiseaseTagQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.DiseaseTagRepositoryImpl;
import com.comebackhome.calendar.infrastructure.repository.ScheduleQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.ScheduleRepositoryImpl;
import com.comebackhome.config.QuerydslConfig;
import com.comebackhome.disease.infrastructure.repository.DiseaseJdbcRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseRepositoryImpl;
import org.springframework.context.annotation.Import;

@Import({
        QuerydslConfig.class,
        DiseaseQuerydslRepository.class,
        DiseaseRepositoryImpl.class,
        DiseaseTagQuerydslRepository.class,
        DiseaseTagRepositoryImpl.class,
        ScheduleQuerydslRepository.class,
        ScheduleRepositoryImpl.class,
        DiseaseJdbcRepository.class
})
public abstract class QuerydslRepositoryTest extends JpaRepositoryTest{
}
