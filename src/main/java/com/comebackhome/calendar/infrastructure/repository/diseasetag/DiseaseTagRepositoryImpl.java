package com.comebackhome.calendar.infrastructure.repository.diseasetag;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.DiseaseTagRepository;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DiseaseTagRepositoryImpl implements DiseaseTagRepository {

    private final DiseaseTagQuerydslRepository diseaseTagQuerydslRepository;
    private final DiseaseTagJpaRepository diseaseTagJpaRepository;

    @Override
    public List<DiseaseTagResponseDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType) {
        return diseaseTagQuerydslRepository.findAllDiseaseTagExceptDiseaseType(diseaseType);
    }

    @Override
    public List<Long> findDiseaseTagIdListByDiseaseTagNameList(List<String> diseaseTagNameList) {
        return diseaseTagQuerydslRepository.findDiseaseTagIdListByDiseaseTagNameList(diseaseTagNameList);
    }

    @Override
    public List<DiseaseTag> findDiseaseTagListByDiseaseTagNameList(List<String> diseaseTagNameList) {
        return diseaseTagQuerydslRepository.findDiseaseTagListByDiseaseTagNameList(diseaseTagNameList);
    }

    @Override
    public List<Long> saveAll(List<DiseaseTag> diseaseTagList) {
        return diseaseTagJpaRepository.saveAll(diseaseTagList)
                .parallelStream()
                .map(DiseaseTag::getId)
                .collect(Collectors.toList());
    }



}
