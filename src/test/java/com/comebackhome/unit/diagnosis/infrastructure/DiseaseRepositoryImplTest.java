package com.comebackhome.unit.diagnosis.infrastructure;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import com.comebackhome.diagnosis.infrastructure.repository.disease.DiseaseJpaRepository;
import com.comebackhome.diagnosis.infrastructure.repository.disease.DiseaseRepositoryImpl;
import com.comebackhome.support.QuerydslRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DiseaseRepositoryImplTest extends QuerydslRepositoryTest {

    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired DiseaseRepositoryImpl diseaseRepository;

    @BeforeEach
    void setup(){
        diseaseJpaRepository.deleteAllInBatch();
    }

    @Test
    void 질병이름으로_SimpleDiseaseResponseDto_찾기() throws Exception{
        //given
        Disease disease = diseaseJpaRepository.save(givenDisease());

        //when
        SimpleDiseaseResponseDto result = diseaseRepository.findSimpleDiseaseResponseDtoByName(disease.getName());

        //then
        assertThat(result.getDiseaseId()).isEqualTo(disease.getId());
        assertThat(result.getName()).isEqualTo(disease.getName());
        assertThat(result.getDefinition()).isEqualTo(disease.getDefinition());
        assertThat(result.getRecommendDepartment()).isEqualTo(disease.getRecommendDepartment());
    }

    @Test
    void 없는_질병이름으로_SimpleDiseaseResponseDto_찾기() throws Exception{
        //when then
        assertThatThrownBy(
                () -> diseaseRepository.findSimpleDiseaseResponseDtoByName(""))
                .isInstanceOf(DiseaseNotFoundException.class);
    }

    @Test
    void diseaseId값으로_Disease_찾기() throws Exception{
        //given
        Disease disease = diseaseJpaRepository.save(givenDisease());

        //when
        Disease result = diseaseRepository.findById(disease.getId()).get();

        //then
        assertThat(result.getId()).isEqualTo(disease.getId());
    }

    @Test
    void disease_벌크_insert() throws Exception{
        //given
        List<Disease> diseaseList = List.of(
                givenDisease("질병1"),
                givenDisease("질병2"),
                givenDisease("질병3")
        );

        //when
        diseaseRepository.saveAll(diseaseList);

        //then
        List<Disease> result = diseaseJpaRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("질병1");
        assertThat(result.get(1).getName()).isEqualTo("질병2");
        assertThat(result.get(2).getName()).isEqualTo("질병3");
    }

    @Test
    void 이름으로_diseaesId_찾기() throws Exception{
        //given
        String diseaseName = diseaseJpaRepository.save(givenDisease()).getName();

        //when
        Optional<Long> result = diseaseRepository.findIdByName(diseaseName);

        //then
        assertThat(result).isNotEmpty();
    }


}
