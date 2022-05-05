package com.comebackhome.integration.disease;

import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.repository.DiseaseRepository;
import com.comebackhome.diagnosis.domain.disease.service.DiseaseService;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import com.comebackhome.diagnosis.infrastructure.repository.disease.DiseaseJpaRepository;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.support.helper.DiseaseGivenHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@EnableCaching
public class DiseaseCachingTest extends IntegrationTest {

    @Autowired CacheManager cacheManager;
    @Autowired DiseaseJpaRepository diseaseJpaRepository;
    @Autowired DiseaseService diseaseService;
    @Autowired DiseaseRepository diseaseRepository;

    @BeforeEach
    void clear(){
        cacheManager.getCache("disease").clear();
        cacheManager.getCache("simpleDisease").clear();
    }

    @Test
    void DiseaseService클래스의_getDisease메서드_결과값_캐싱() throws Exception{
        //given
        Disease disease = diseaseJpaRepository.save(DiseaseGivenHelper.givenDisease());

        //when
        diseaseService.getDisease(disease.getId());

        //then
        Optional<DiseaseResponseDto> result = Optional.ofNullable(cacheManager.getCache("disease"))
                .map(cache -> cache.get(disease.getId(), DiseaseResponseDto.class));
        assertThat(result).isNotEmpty();
    }

    @Test
    void DiseaseRepositoryImpl클래스의_findSimpleDiseaseQueryDtoByName메서드_결과값_캐싱() throws Exception{
        //given
        Disease disease = diseaseJpaRepository.save(DiseaseGivenHelper.givenDisease());

        //when
        diseaseRepository.findSimpleDiseaseQueryDtoByName(disease.getName());

        //then
        Optional<SimpleDiseaseResponseDto> result = Optional.ofNullable(cacheManager.getCache("simpleDisease"))
                .map(cache -> cache.get(disease.getName(), SimpleDiseaseResponseDto.class));
        assertThat(result).isNotEmpty();

    }

}
