package com.comebackhome.integration;

import com.comebackhome.calendar.application.DiseaseTagService;
import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.infrastructure.DiseaseTagJpaRepository;
import com.comebackhome.support.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.calendar.domain.DiseaseType.*;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseTagCachingTest extends IntegrationTest {

    @Autowired CacheManager cacheManager;
    @Autowired DiseaseTagJpaRepository diseaseTagJpaRepository;
    @Autowired DiseaseTagService diseaseTagService;

    @BeforeEach
    void clear(){
        cacheManager.getCache("diseaseTag").clear();
    }

    @Test
    void diseaseTagService_클래스의_getDiseaseTagExceptCustomType_메서드_결과값_캐싱() throws Exception{
        //given
        createDiseaseTagListAndSave();

        //when
        diseaseTagService.getDiseaseTagExceptCustomType();

        //then
        Optional<DiseaseTagResponseDto> result = Optional.ofNullable(cacheManager.getCache("diseaseTag"))
                .map(cache -> cache.get("tags", DiseaseTagResponseDto.class));
        assertThat(result).isNotEmpty();
    }

    private void createDiseaseTagListAndSave() {
        List<DiseaseTag> diseaseTagList = List.of(givenDiseaseTag(HEAD, "두통"),
                givenDiseaseTag(BRONCHUS, "코막힘"),
                givenDiseaseTag(CHEST, "가슴 통증"),
                givenDiseaseTag(STOMACH, "공복감"),
                givenDiseaseTag(LIMB, "관절통"),
                givenDiseaseTag(SKIN, "여드름"));

        diseaseTagJpaRepository.saveAll(diseaseTagList);
    }
}
