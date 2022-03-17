package com.comebackhome.disease.application;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.disease.domain.DiseaseRepository;
import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DiseaseService implements DiseaseQueryUseCase{

    private final DiseaseRepository diseaseRepository;

    @Override
    public DiseaseResponseDto getDisease(Long diseaseId) {
        return DiseaseResponseDto.from(diseaseRepository.findDiseaseQueryDtoById(diseaseId));
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
}
