package com.comebackhome.disease.application;

import com.comebackhome.disease.application.dto.DiseaseResponseDto;
import com.comebackhome.disease.domain.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiseaseService implements DiseaseQueryUseCase{

    private final DiseaseRepository diseaseRepository;

    @Override
    public DiseaseResponseDto getDisease(Long diseaseId) {
        return DiseaseResponseDto.from(diseaseRepository.findDiseaseQueryDtoById(diseaseId));
    }
}
