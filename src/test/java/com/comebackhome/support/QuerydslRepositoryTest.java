package com.comebackhome.support;

import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagRepositoryImpl;
import com.comebackhome.calendar.infrastructure.repository.schedule.ScheduleQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.schedule.ScheduleRepositoryImpl;
import com.comebackhome.config.QuerydslConfig;
import com.comebackhome.disease.infrastructure.repository.diagnosis.DiagnosisQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.diagnosis.DiagnosisRepositoryImpl;
import com.comebackhome.disease.infrastructure.repository.diagnosisdisease.DiagnosisDiseaseJdbcRepository;
import com.comebackhome.disease.infrastructure.repository.diagnosisdisease.DiagnosisDiseaseRepositoryImpl;
import com.comebackhome.disease.infrastructure.repository.disease.DiseaseJdbcRepository;
import com.comebackhome.disease.infrastructure.repository.disease.DiseaseQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.disease.DiseaseRepositoryImpl;
import org.springframework.context.annotation.Import;

@Import({
        QuerydslConfig.class,
        DiseaseQuerydslRepository.class,
        DiseaseRepositoryImpl.class,
        DiseaseTagQuerydslRepository.class,
        DiseaseTagRepositoryImpl.class,
        ScheduleQuerydslRepository.class,
        ScheduleRepositoryImpl.class,
        DiseaseJdbcRepository.class,
        DiagnosisDiseaseJdbcRepository.class,
        DiagnosisDiseaseRepositoryImpl.class,
        DiagnosisQuerydslRepository.class,
        DiagnosisRepositoryImpl.class
})
public abstract class QuerydslRepositoryTest extends JpaRepositoryTest{
}
