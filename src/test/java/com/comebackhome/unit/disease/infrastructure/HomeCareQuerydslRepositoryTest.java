package com.comebackhome.unit.disease.infrastructure;

import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.infrastructure.repository.DiseaseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.HomeCareJpaRepository;
import com.comebackhome.disease.infrastructure.repository.HomeCareQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.dto.HomeCareQueryDto;
import com.comebackhome.support.QuerydslRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenHomeCare;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeCareQuerydslRepositoryTest extends QuerydslRepositoryTest {

    @Autowired
    DiseaseJpaRepository diseaseJpaRepository;

    @Autowired
    HomeCareQuerydslRepository homeCareQuerydslRepository;

    @Autowired
    HomeCareJpaRepository homeCareJpaRepository;

    @Test
    void DiseaseId로_HomeCareQueryDto_찾기() throws Exception{
        //given
        String solution1 = "해결책1";
        String solution2 = "해결책2";
        Disease disease = diseaseJpaRepository.save(givenDisease());
        homeCareJpaRepository.save(givenHomeCare(disease,solution1));
        homeCareJpaRepository.save(givenHomeCare(disease,solution2));

        //when
        List<HomeCareQueryDto> result = homeCareQuerydslRepository.findHomeCareByDiseaseId(disease.getId());

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getSolution()).isEqualTo(solution1);
        assertThat(result.get(1).getSolution()).isEqualTo(solution2);
    }
}
