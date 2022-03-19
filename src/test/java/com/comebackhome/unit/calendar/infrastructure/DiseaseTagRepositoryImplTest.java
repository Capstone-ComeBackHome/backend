package com.comebackhome.unit.calendar.infrastructure;

import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import com.comebackhome.calendar.infrastructure.DiseaseTagJpaRepository;
import com.comebackhome.calendar.infrastructure.DiseaseTagRepositoryImpl;
import com.comebackhome.support.QuerydslRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagRepositoryImplTest extends QuerydslRepositoryTest {

    @Autowired DiseaseTagRepositoryImpl diseaseTagRepository;
    @Autowired DiseaseTagJpaRepository diseaseTagJpaRepository;

    @Test
    void 특정_diseaseType을_제외한_diseaseTag_모두_가져오기() throws Exception{
        //given
        List<DiseaseTag> diseaseTagList = new ArrayList<>();
        diseaseTagList.add(givenDiseaseTag(DiseaseType.CHEST,"가슴통증"));
        diseaseTagList.add(givenDiseaseTag(DiseaseType.BRONCHUS,"구내염"));
        diseaseTagList.add(givenDiseaseTag(DiseaseType.HEAD,"강박증"));

        diseaseTagJpaRepository.saveAll(diseaseTagList);

        //when
        List<DiseaseTagQueryDto> result = diseaseTagRepository.findAllDiseaseTagExceptDiseaseType(DiseaseType.CHEST);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getDiseaseType()).isEqualTo(DiseaseType.BRONCHUS);
        assertThat(result.get(0).getName()).isEqualTo("구내염");
        assertThat(result.get(1).getDiseaseType()).isEqualTo(DiseaseType.HEAD);
        assertThat(result.get(1).getName()).isEqualTo("강박증");
    }
}
