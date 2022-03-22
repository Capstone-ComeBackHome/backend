package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.DiseaseRepository;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiseaseRepositoryImpl implements DiseaseRepository {

    private final DiseaseJpaRepository diseaseJpaRepository;
    private final DiseaseQuerydslRepository diseaseQuerydslRepository;

    @Override
    public Optional<Disease> findDiseaseById(Long diseaseId) {
        return diseaseJpaRepository.findById(diseaseId);
    }

    @Override
    @Cacheable(value = "simpleDisease",
            key = "#diseaseName",
            unless = "#result == null"
    )
    public SimpleDiseaseQueryDto findSimpleDiseaseQueryDtoByName(String diseaseName) {
        return diseaseQuerydslRepository.findDiseaseSimpleQueryDtoByName(diseaseName)
                .orElseThrow(() -> new DiseaseNotFoundException());
    }
}
