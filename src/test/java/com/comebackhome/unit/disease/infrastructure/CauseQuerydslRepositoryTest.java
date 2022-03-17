package com.comebackhome.unit.disease.infrastructure;

import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.infrastructure.repository.CauseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.CauseQuerydslRepository;
import com.comebackhome.disease.infrastructure.repository.DiseaseJpaRepository;
import com.comebackhome.disease.infrastructure.repository.dto.CauseQueryDto;
import com.comebackhome.support.QuerydslRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenCause;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDisease;
import static org.assertj.core.api.Assertions.assertThat;

public class CauseQuerydslRepositoryTest extends QuerydslRepositoryTest {

    @Autowired CauseJpaRepository causeJpaRepository;
    @Autowired CauseQuerydslRepository causeQuerydslRepository;
    @Autowired DiseaseJpaRepository diseaseJpaRepository;

    @Test
    void DiseaseId로_CauseQueryDto_찾기() throws Exception{
        //given
        String reason1 = "원인1";
        String reason2 = "원인2";
        Disease disease = diseaseJpaRepository.save(givenDisease());
        causeJpaRepository.save(givenCause(disease,reason1));
        causeJpaRepository.save(givenCause(disease,reason2));

        //when
        List<CauseQueryDto> result = causeQuerydslRepository.findCauseQueryDtoByDiseaseId(disease.getId());

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getReason()).isEqualTo(reason1);
        assertThat(result.get(1).getReason()).isEqualTo(reason2);
    }
}
