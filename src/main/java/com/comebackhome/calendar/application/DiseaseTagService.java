package com.comebackhome.calendar.application;

import com.comebackhome.calendar.application.dto.DiseaseTagDto;
import com.comebackhome.calendar.application.dto.DiseaseTagResponseDto;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import com.comebackhome.calendar.domain.repository.DiseaseTagRepository;
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
    public DiseaseTagResponseDto getDiseaseTagExceptCustomType() {
        List<DiseaseTagQueryDto> diseaseTagList = diseaseTagRepository.findAllDiseaseTagExceptDiseaseType(DiseaseType.CUSTOM);
        DiseaseTagResponseDto diseaseTagResponseDto = new DiseaseTagResponseDto();
        setDiseaseTagResponseDtoByDiseaseType(diseaseTagList, diseaseTagResponseDto);
        return diseaseTagResponseDto;
    }

    private void setDiseaseTagResponseDtoByDiseaseType(List<DiseaseTagQueryDto> diseaseTagList, DiseaseTagResponseDto diseaseTagResponseDto) {
        diseaseTagResponseDto.setHeadDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.HEAD));
        diseaseTagResponseDto.setBronchusDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.BRONCHUS));
        diseaseTagResponseDto.setChestDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.CHEST));
        diseaseTagResponseDto.setStomachDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.STOMACH));
        diseaseTagResponseDto.setLimbDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.LIMB));
        diseaseTagResponseDto.setSkinDiseaseTagList(getDiseaseTagList(diseaseTagList, DiseaseType.SKIN));
    }

    private List<DiseaseTagDto> getDiseaseTagList(List<DiseaseTagQueryDto> diseaseTagList, DiseaseType type) {
        return diseaseTagList.parallelStream()
                .filter(diseaseTag -> diseaseTag.getDiseaseType().equals(type))
                .map(DiseaseTagDto::from)
                .collect(Collectors.toList());
    }
}
