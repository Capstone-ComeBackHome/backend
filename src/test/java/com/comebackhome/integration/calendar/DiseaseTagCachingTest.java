package com.comebackhome.integration.calendar;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.service.DiseaseTagService;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DefaultTypeDiseaseTagListResponseDto;
import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagJpaRepository;
import com.comebackhome.support.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;
import java.util.Optional;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.*;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static org.assertj.core.api.Assertions.assertThat;

@EnableCaching
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
        Optional<DefaultTypeDiseaseTagListResponseDto> result = Optional.ofNullable(cacheManager.getCache("diseaseTag"))
                .map(cache -> cache.get("tags", DefaultTypeDiseaseTagListResponseDto.class));
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
