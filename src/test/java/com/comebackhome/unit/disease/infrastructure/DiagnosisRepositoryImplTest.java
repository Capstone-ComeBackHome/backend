package com.comebackhome.unit.disease.infrastructure;

import com.comebackhome.disease.domain.Diagnosis;
import com.comebackhome.disease.infrastructure.repository.DiagnosisJpaRepository;
import com.comebackhome.disease.infrastructure.repository.DiagnosisRepositoryImpl;
import com.comebackhome.support.JpaRepositoryTest;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosis;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisRepositoryImplTest extends JpaRepositoryTest {

    @Autowired DiagnosisJpaRepository diagnosisJpaRepository;
    @Autowired DiagnosisRepositoryImpl diagnosisRepository;
    @Autowired UserJpaRepository userJpaRepository;

    @Test
    void diagnosis_저장() throws Exception{
        //given
        Long userId = userJpaRepository.save(givenUser()).getId();
        Diagnosis diagnosis = givenDiagnosis(userId);

        //when
        Long diagnosisId = diagnosisRepository.save(diagnosis);

        //then
        Optional<Diagnosis> result = diagnosisJpaRepository.findById(diagnosisId);
        assertThat(result).isNotEmpty();
    }
}
