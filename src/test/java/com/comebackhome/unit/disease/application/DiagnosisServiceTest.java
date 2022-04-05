package com.comebackhome.unit.disease.application;


import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.application.DiagnosisService;
import com.comebackhome.disease.domain.DiagnosisDiseaseRepository;
import com.comebackhome.disease.domain.DiagnosisRepository;
import com.comebackhome.disease.domain.DiseaseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
        Assertions.assertThatThrownBy(() -> diagnosisService.createDiagnosis(List.of("질병1","질병2","질병3"),anyLong()))
        .isInstanceOf(DiseaseNotFoundException.class);
    }
}
