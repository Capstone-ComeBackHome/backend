package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.DiseaseRepository;
import com.comebackhome.disease.domain.dto.DiseaseQueryDto;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.CauseQueryDto;
import com.comebackhome.disease.infrastructure.repository.dto.HomeCareQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiseaseRepositoryImpl implements DiseaseRepository {

    private final DiseaseJpaRepository diseaseJpaRepository;
    private final HomeCareQuerydslRepository homeCareQuerydslRepository;
    private final CauseQuerydslRepository causeQuerydslRepository;
    private final DiseaseQuerydslRepository diseaseQuerydslRepository;

    @Override
    public DiseaseQueryDto findDiseaseQueryDtoById(Long diseaseId) {
        Disease disease = diseaseJpaRepository.findById(diseaseId)
                .orElseThrow(() -> new DiseaseNotFoundException());
        List<CauseQueryDto> causeQueryDtoList
                = causeQuerydslRepository.findCauseQueryDtoByDiseaseId(diseaseId);
        List<HomeCareQueryDto> homeCareQueryDtoList
                = homeCareQuerydslRepository.findHomeCareByDiseaseId(diseaseId);

        return DiseaseQueryDto.of(disease, causeQueryDtoList, homeCareQueryDtoList);
    }

    @Override
    public SimpleDiseaseQueryDto findSimpleDiseaseQueryDtoByName(String diseaseName) {
        return diseaseQuerydslRepository.findDiseaseSimpleQueryDtoByName(diseaseName)
                .orElseThrow(() -> new DiseaseNotFoundException());
    }
}
