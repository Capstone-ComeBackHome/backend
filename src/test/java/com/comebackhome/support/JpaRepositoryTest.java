package com.comebackhome.support;

import com.comebackhome.calendar.infrastructure.repository.schedulediseasetag.ScheduleDiseaseTagRepositoryImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({ScheduleDiseaseTagRepositoryImpl.class
})
@DataJpaTest
public abstract class JpaRepositoryTest {
}
