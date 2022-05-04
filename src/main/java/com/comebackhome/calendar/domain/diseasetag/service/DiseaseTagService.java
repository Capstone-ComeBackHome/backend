package com.comebackhome.calendar.domain.diseasetag.service;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTagRepository;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiseaseTagService implements DiseaseTagQueryUseCase{

    private final DiseaseTagRepository diseaseTagRepository;

    @Cacheable(value = "diseaseTag",
            key = "'tags'",
            unless = "#result == null"
    )
    @Override
    public DiseaseTagListResponseDto getDiseaseTagExceptCustomType() {
        List<DiseaseTagResponseDto> diseaseTagList = diseaseTagRepository.findAllDiseaseTagExceptDiseaseType(DiseaseType.CUSTOM);
        DiseaseTagListResponseDto diseaseTagListResponseDto = new DiseaseTagListResponseDto();
        setDiseaseTagResponseDtoByDiseaseType(diseaseTagList, diseaseTagListResponseDto);
        return diseaseTagListResponseDto;
    }

    private void setDiseaseTagResponseDtoByDiseaseType(List<DiseaseTagResponseDto> diseaseTagList, DiseaseTagListResponseDto diseaseTagListResponseDto) {
        diseaseTagListResponseDto.setHeadDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.HEAD));
        diseaseTagListResponseDto.setBronchusDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.BRONCHUS));
        diseaseTagListResponseDto.setChestDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.CHEST));
        diseaseTagListResponseDto.setStomachDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.STOMACH));
        diseaseTagListResponseDto.setLimbDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.LIMB));
        diseaseTagListResponseDto.setSkinDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.SKIN));
    }

    private List<DiseaseTagResponseDto> getDiseaseTagList(List<DiseaseTagResponseDto> diseaseTagList, DiseaseType type) {
        return diseaseTagList.parallelStream()
                .filter(diseaseTag -> diseaseTag.getDiseaseType().equals(type))
                .collect(Collectors.toList());
    }
}
