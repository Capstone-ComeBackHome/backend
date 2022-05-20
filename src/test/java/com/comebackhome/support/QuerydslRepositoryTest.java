package com.comebackhome.support;

import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagRepositoryImpl;
import com.comebackhome.calendar.infrastructure.repository.schedule.ScheduleQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.schedule.ScheduleRepositoryImpl;
import com.comebackhome.calendar.infrastructure.repository.schedulediseasetag.ScheduleDiseaseTagQuerydslRepository;
import com.comebackhome.calendar.infrastructure.repository.schedulediseasetag.ScheduleDiseaseTagRepositoryImpl;
import com.comebackhome.config.QuerydslConfig;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosis.DiagnosisQuerydslRepository;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosis.DiagnosisRepositoryImpl;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosisdisease.DiagnosisDiseaseJdbcRepository;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosisdisease.DiagnosisDiseaseRepositoryImpl;
import com.comebackhome.diagnosis.infrastructure.repository.disease.DiseaseJdbcRepository;
import com.comebackhome.diagnosis.infrastructure.repository.disease.DiseaseQuerydslRepository;
import com.comebackhome.diagnosis.infrastructure.repository.disease.DiseaseRepositoryImpl;
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
        DiagnosisRepositoryImpl.class,
        ScheduleDiseaseTagQuerydslRepository.class,
        ScheduleDiseaseTagRepositoryImpl.class
})
public abstract class QuerydslRepositoryTest extends JpaRepositoryTest{
}
