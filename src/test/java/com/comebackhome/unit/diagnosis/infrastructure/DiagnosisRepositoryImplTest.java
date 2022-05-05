package com.comebackhome.unit.diagnosis.infrastructure;

import com.comebackhome.diagnosis.domain.Diagnosis;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosis.DiagnosisJpaRepository;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosis.DiagnosisQuerydslRepository;
import com.comebackhome.diagnosis.infrastructure.repository.diagnosis.DiagnosisRepositoryImpl;
import com.comebackhome.support.QuerydslRepositoryTest;
import com.comebackhome.user.infrastructure.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosis;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosisRepositoryImplTest extends QuerydslRepositoryTest {

    @Autowired DiagnosisJpaRepository diagnosisJpaRepository;
    @Autowired DiagnosisRepositoryImpl diagnosisRepository;
    @Autowired UserJpaRepository userJpaRepository;
    @Autowired DiagnosisQuerydslRepository diagnosisQuerydslRepository;

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

    @Test
    void diagnosis_삭제() throws Exception{
        //given
        Long userId = userJpaRepository.save(givenUser()).getId();
        Long diagnosisId = diagnosisJpaRepository.save(givenDiagnosis(userId)).getId();

        //when
        diagnosisRepository.deleteById(diagnosisId);

        //then
        Optional<Diagnosis> result = diagnosisJpaRepository.findById(diagnosisId);
        assertThat(result).isEmpty();
    }

    @Test
    void 진단_내역_페이징_조회하기_다음_페이지_없음() throws Exception{
        //given
        Long userId = userJpaRepository.save(givenUser()).getId();
        List<Diagnosis> diagnosisList = diagnosisJpaRepository.saveAll(List.of(
                givenDiagnosis(userId),
                givenDiagnosis(userId),
                givenDiagnosis(userId)
        ));

        PageRequest pageRequest = PageRequest.of(0, 20);

        //when
        Slice<Diagnosis> result = diagnosisRepository
                .findDiagnosisListByLastDiagnosisIdAndUserId(diagnosisList.get(2).getId() + 1,
                userId, pageRequest);

        //then
        assertThat(result.getContent().size()).isEqualTo(3);
        assertThat(result.hasNext()).isFalse();
    }

    @Test
    void 진단_내역_페이징_조회하기_다음_페이지_있음() throws Exception{
        //given
        Long userId = userJpaRepository.save(givenUser()).getId();
        List<Diagnosis> diagnosisList = diagnosisJpaRepository.saveAll(List.of(
                givenDiagnosis(userId),
                givenDiagnosis(userId),
                givenDiagnosis(userId)
        ));

        PageRequest pageRequest = PageRequest.of(0, 1);

        //when
        Slice<Diagnosis> result = diagnosisRepository
                .findDiagnosisListByLastDiagnosisIdAndUserId(diagnosisList.get(2).getId() + 1,
                        userId, pageRequest);

        //then
        assertThat(result.getContent().size()).isEqualTo(1);
        assertThat(result.hasNext()).isTrue();
    }

    @Test
    void 최초_진단_내역_페이징_조회하기() throws Exception{
        //given
        Long userId = userJpaRepository.save(givenUser()).getId();
        List<Diagnosis> diagnosisList = diagnosisJpaRepository.saveAll(List.of(
                givenDiagnosis(userId),
                givenDiagnosis(userId),
                givenDiagnosis(userId)
        ));

        PageRequest pageRequest = PageRequest.of(0, 20);

        //when
        Slice<Diagnosis> result = diagnosisRepository
                .findDiagnosisListByLastDiagnosisIdAndUserId(null,
                        userId, pageRequest);

        //then
        assertThat(result.getContent().size()).isEqualTo(3);
        assertThat(result.hasNext()).isFalse();
    }
}
