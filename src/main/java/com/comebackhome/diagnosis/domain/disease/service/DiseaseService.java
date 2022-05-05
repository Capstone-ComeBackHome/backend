package com.comebackhome.diagnosis.domain.disease.service;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.diagnosis.domain.disease.Disease;
import com.comebackhome.diagnosis.domain.disease.repository.DiseaseRepository;
import com.comebackhome.diagnosis.domain.disease.service.dto.request.DiseaseSaveRequestDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.DiseaseResponseDto;
import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import lombok.RequiredArgsConstructor;
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

    @Override
    public DiseaseResponseDto getDisease(Long diseaseId) {
        return DiseaseResponseDto.from(diseaseRepository.findDiseaseById(diseaseId)
                .orElseThrow(() -> new DiseaseNotFoundException()));
    }

    @Override
    public List<SimpleDiseaseResponseDto> getSimpleDiseaseList(List<String> diseaseNameList) {
        List<SimpleDiseaseResponseDto> simpleDiseaseResponseDtoList = new ArrayList<>();
        for (String diseaseName : diseaseNameList) {
            SimpleDiseaseResponseDto simpleDiseaseResponseDto = diseaseRepository.findSimpleDiseaseQueryDtoByName(diseaseName);
            simpleDiseaseResponseDtoList.add(simpleDiseaseResponseDto);
        }
        return simpleDiseaseResponseDtoList;
    }

    @Override
    @Transactional
    public void createDisease(List<DiseaseSaveRequestDto> diseaseSaveRequestDto) {
        List<Disease> diseaseList = diseaseSaveRequestDto.parallelStream()
                .map(DiseaseSaveRequestDto::toEntity)
                .collect(Collectors.toList());
        diseaseRepository.saveAll(diseaseList);
    }

}
