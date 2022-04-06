package com.comebackhome.disease.application;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.application.dto.DiseaseRequestDto;
import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.Disease;
import com.comebackhome.disease.domain.repository.DiseaseRepository;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiseaseService implements DiseaseQueryUseCase, DiseaseCommandUseCase{

    private final DiseaseRepository diseaseRepository;

    @Cacheable(value = "disease",
            key = "#diseaseId",
            unless = "#result == null"
    )
    @Override
    public DiseaseResponseDto getDisease(Long diseaseId) {
        return DiseaseResponseDto.from(diseaseRepository.findDiseaseById(diseaseId)
                .orElseThrow(() -> new DiseaseNotFoundException()));
    }

    @Override
    public List<SimpleDiseaseResponseDto> getSimpleDiseaseList(List<String> diseaseNameList) {
        List<SimpleDiseaseResponseDto> simpleDiseaseResponseDtoList = new ArrayList<>();
        for (String diseaseName : diseaseNameList) {
            SimpleDiseaseQueryDto simpleDiseaseQueryDto = diseaseRepository.findSimpleDiseaseQueryDtoByName(diseaseName);
            SimpleDiseaseResponseDto simpleDiseaseResponseDto = SimpleDiseaseResponseDto.from(simpleDiseaseQueryDto);
            simpleDiseaseResponseDtoList.add(simpleDiseaseResponseDto);
        }
        return simpleDiseaseResponseDtoList;
    }

    @Override
    @Transactional
    public void createDisease(List<DiseaseRequestDto> diseaseRequestDto) {
        List<Disease> diseaseList = diseaseRequestDto.parallelStream()
                .map(DiseaseRequestDto::toDisease)
                .collect(Collectors.toList());
        diseaseRepository.saveAll(diseaseList);
    }

}
