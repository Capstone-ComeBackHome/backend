package com.comebackhome.calendar.domain.diseasetag.service;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.repository.DiseaseTagRepository;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DefaultTypeDiseaseTagListResponseDto;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagListResponseDto;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiseaseTagService implements DiseaseTagQueryUseCase{

    private final DiseaseTagRepository diseaseTagRepository;


    @Override
    public DefaultTypeDiseaseTagListResponseDto getDiseaseTagExceptCustomType() {
        List<DiseaseTagQueryDto> diseaseTagQueryDtoList
                = diseaseTagRepository.findAllDiseaseTagExceptDiseaseType(DiseaseType.CUSTOM);
        DefaultTypeDiseaseTagListResponseDto defaultTypeDiseaseTagListResponseDto = new DefaultTypeDiseaseTagListResponseDto();
        setDiseaseTagResponseDtoByDiseaseType(diseaseTagQueryDtoList, defaultTypeDiseaseTagListResponseDto);
        return defaultTypeDiseaseTagListResponseDto;
    }

    private void setDiseaseTagResponseDtoByDiseaseType(List<DiseaseTagQueryDto> diseaseTagList,
                                                       DefaultTypeDiseaseTagListResponseDto defaultTypeDiseaseTagListResponseDto) {

        defaultTypeDiseaseTagListResponseDto.setHead(getDiseaseTagResponseDto(DiseaseType.HEAD,diseaseTagList));
        defaultTypeDiseaseTagListResponseDto.setBronchus(getDiseaseTagResponseDto(DiseaseType.BRONCHUS,diseaseTagList));
        defaultTypeDiseaseTagListResponseDto.setChest(getDiseaseTagResponseDto(DiseaseType.CHEST,diseaseTagList));
        defaultTypeDiseaseTagListResponseDto.setStomach(getDiseaseTagResponseDto(DiseaseType.STOMACH,diseaseTagList));
        defaultTypeDiseaseTagListResponseDto.setLimb(getDiseaseTagResponseDto(DiseaseType.LIMB,diseaseTagList));
        defaultTypeDiseaseTagListResponseDto.setSkin(getDiseaseTagResponseDto(DiseaseType.SKIN,diseaseTagList));
    }

    private DiseaseTagListResponseDto getDiseaseTagResponseDto(DiseaseType diseaseType, List<DiseaseTagQueryDto> diseaseTagList) {
        return DiseaseTagListResponseDto.of(diseaseType.getDescription(), getDiseaseTagQueryDtoList(diseaseTagList, diseaseType));
    }

    private List<DiseaseTagQueryDto> getDiseaseTagQueryDtoList(List<DiseaseTagQueryDto> diseaseTagList, DiseaseType type) {
        return diseaseTagList.parallelStream()
                .filter(diseaseTag -> diseaseTag.getDiseaseType().equals(type))
                .collect(Collectors.toList());
    }
}
