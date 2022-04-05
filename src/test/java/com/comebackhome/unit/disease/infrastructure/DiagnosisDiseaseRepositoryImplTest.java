package com.comebackhome.unit.disease.infrastructure;

import com.comebackhome.disease.domain.DiagnosisDisease;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.infrastructure.repository.*;
import com.comebackhome.support.QuerydslRepositoryTest;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosis;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisDiseaseRepositoryImplTest extends QuerydslRepositoryTest {

    @Autowired DiagnosisDiseaseRepositoryImpl diagnosisDiseaseRepository;
    @Autowired DiagnosisDiseaseJdbcRepository diagnosisDiseaseJdbcRepository;
    @Autowired DiagnosisJpaRepository diagnosisJpaRepository;
    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired UserJpaRepository userJpaRepository;
    @Autowired DiagnosisDiseaseJpaRepository diagnosisDiseaseJpaRepository;

    @Test
    void diagnosisDisease_벌크_저장하기() throws Exception{
        //given
        Long userId = userJpaRepository.save(givenUser()).getId();
        Long diagnosisId = diagnosisJpaRepository.save(givenDiagnosis(userId)).getId();
        List<Disease> diseaseList = diseaseJpaRepository.saveAll(
                List.of(
                givenDisease("질병1"),
                givenDisease("질병2"),
                givenDisease("질병3"))
        );

        List<DiagnosisDisease> diagnosisDiseaseList = new ArrayList<>();
        for (int order = 0; order < diseaseList.size(); order++){
            Long diseaseId = diseaseList.get(order).getId();
            diagnosisDiseaseList.add(DiagnosisDisease.of(diseaseId, diagnosisId,order+1));
        }

        //when
        diagnosisDiseaseRepository.saveAll(diagnosisDiseaseList);

        //then
        List<DiagnosisDisease> result = diagnosisDiseaseJpaRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }
}
