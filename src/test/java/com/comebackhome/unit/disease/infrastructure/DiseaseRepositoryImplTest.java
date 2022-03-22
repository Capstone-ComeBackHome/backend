package com.comebackhome.unit.disease.infrastructure;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import com.comebackhome.disease.infrastructure.repository.*;
import com.comebackhome.support.QuerydslRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.comebackhome.support.helper.DiseaseGivenHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiseaseRepositoryImplTest extends QuerydslRepositoryTest {

    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired HomeCareQuerydslRepository homeCareQuerydslRepository;
    @Autowired CauseQuerydslRepository causeQuerydslRepository;
    @Autowired CauseJpaRepository causeJpaRepository;
    @Autowired HomeCareJpaRepository homeCareJpaRepository;
    @Autowired DiseaseRepositoryImpl diseaseRepository;

    @BeforeEach
    void setup(){
        causeJpaRepository.deleteAllInBatch();
        homeCareJpaRepository.deleteAllInBatch();
        diseaseJpaRepository.deleteAllInBatch();
    }

    @Test
    void DiseaseId로_DiseaseQueryDto_찾기() throws Exception{
        //given
        String reason = "원인";
        String solution = "해결책";
        Disease disease = diseaseJpaRepository.save(givenDisease());
        causeJpaRepository.save(givenCause(disease,reason));
        homeCareJpaRepository.save(givenHomeCare(disease,solution));

        //when
        DiseaseQueryDto result = diseaseRepository.findDiseaseQueryDtoById(disease.getId());

        //then
        assertThat(result.getName()).isEqualTo(disease.getName());
        assertThat(result.getDefinition()).isEqualTo(disease.getDefinition());
        assertThat(result.getSymptom()).isEqualTo(disease.getSymptom());
        assertThat(result.getComplications()).isEqualTo(disease.getComplications());
        assertThat(result.getHospitalCare()).isEqualTo(disease.getHospitalCare());
        assertThat(result.getRecommendDepartment()).isEqualTo(disease.getRecommendDepartment());
        assertThat(result.getCauseList().size()).isEqualTo(1);
        assertThat(result.getHomeCareList().size()).isEqualTo(1);
    }

    @Test
    void 없는_DiseaseId로_DiseaseQueryDto_찾기() throws Exception{
        //when then
        assertThatThrownBy(
                () -> diseaseRepository.findDiseaseQueryDtoById(-1L))
                .isInstanceOf(DiseaseNotFoundException.class);
    }

    @Test
    void 질병이름으로_SimpleDiseaseQueryDto_찾기() throws Exception{
        //given
        Disease disease = diseaseJpaRepository.save(givenDisease());

        //when
        SimpleDiseaseQueryDto result = diseaseRepository.findSimpleDiseaseQueryDtoByName(disease.getName());

        //then
        assertThat(result.getDiseaseId()).isEqualTo(disease.getId());
        assertThat(result.getName()).isEqualTo(disease.getName());
        assertThat(result.getDefinition()).isEqualTo(disease.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(disease.getRecommendDepartment());
    }

    @Test
    void 없는_질병이름으로_SimpleDiseaseQueryDto_찾기() throws Exception{
        //when then
        assertThatThrownBy(
                () -> diseaseRepository.findSimpleDiseaseQueryDtoByName(""))
                .isInstanceOf(DiseaseNotFoundException.class);
    }

}
