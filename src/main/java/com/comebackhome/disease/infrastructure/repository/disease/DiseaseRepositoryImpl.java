package com.comebackhome.disease.infrastructure.repository.disease;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import com.comebackhome.disease.domain.repository.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiseaseRepositoryImpl implements DiseaseRepository {

    private final DiseaseJpaRepository diseaseJpaRepository;
    private final DiseaseQuerydslRepository diseaseQuerydslRepository;
    private final DiseaseJdbcRepository diseaseJdbcRepository;

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

    @Override
    public void saveAll(List<Disease> diseaseList) {
        diseaseJdbcRepository.saveAll(diseaseList);
    }

    @Override
    public Optional<Long> findIdByName(String diseaseName) {
        return diseaseJpaRepository.findIdByName(diseaseName);
    }
}
