package com.comebackhome.support;

import com.comebackhome.calendar.infrastructure.repository.ScheduleDiseaseTagRepositoryImpl;
import com.comebackhome.disease.infrastructure.repository.DiagnosisRepositoryImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({ScheduleDiseaseTagRepositoryImpl.class,
        DiagnosisRepositoryImpl.class
})
@DataJpaTest
public abstract class JpaRepositoryTest {
}
