package com.comebackhome.unit.disease.application;


import com.comebackhome.common.exception.disease.DiagnosisNotFoundException;
import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.common.exception.disease.NotMyDiagnosisException;
import com.comebackhome.disease.application.DiagnosisService;
import com.comebackhome.disease.domain.DiagnosisDiseaseRepository;
import com.comebackhome.disease.domain.DiagnosisRepository;
import com.comebackhome.disease.domain.DiseaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosis;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class DiagnosisServiceTest {

    @InjectMocks DiagnosisService diagnosisService;

    @Mock DiseaseRepository diseaseRepository;
    @Mock DiagnosisRepository diagnosisRepository;;
    @Mock DiagnosisDiseaseRepository diagnosisDiseaseRepository;;

    @Test
    void 진단결과_생성하기() throws Exception{
        //given
        given(diseaseRepository.findIdByName(any())).willReturn(Optional.of(1L));

        //when
        diagnosisService.createDiagnosis(List.of("질병1","질병2","질병3"),anyLong());

        //then
        then(diagnosisRepository).should().save(any());
        then(diagnosisDiseaseRepository).should().saveAll(any());
    }

    @Test
    void 없는_질병명_으로_진단결과_생성_시도() throws Exception{
        //when then
        assertThatThrownBy(() -> diagnosisService.createDiagnosis(List.of("질병1","질병2","질병3"),anyLong()))
        .isInstanceOf(DiseaseNotFoundException.class);
    }

    @Test
    void 진단_내역_삭제_성공() throws Exception{
        //given
        given(diagnosisRepository.findById(any())).willReturn(Optional.of(givenDiagnosis(1L)));

        //when
        diagnosisService.deleteMyDiagnosis(any(),1L);

        //then
        then(diagnosisRepository).should().deleteById(any());
        then(diagnosisDiseaseRepository).should().deleteByDiagnosisId(any());
    }

    @Test
    void 존재하지_않는_진단_내역_삭제_실패() throws Exception{
        //when then
        assertThatThrownBy(() -> diagnosisService.deleteMyDiagnosis(any(),1L))
                .isInstanceOf(DiagnosisNotFoundException.class)
        ;
    }

    @Test
    void 타인의_진단_내역_삭제_실패() throws Exception{
        // given
        given(diagnosisRepository.findById(any())).willReturn(Optional.of(givenDiagnosis(1L)));

        //when then
        assertThatThrownBy(() -> diagnosisService.deleteMyDiagnosis(any(),2L))
                .isInstanceOf(NotMyDiagnosisException.class)
        ;
    }


}
