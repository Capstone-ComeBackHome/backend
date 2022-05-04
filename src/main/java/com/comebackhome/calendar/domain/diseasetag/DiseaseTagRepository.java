package com.comebackhome.calendar.domain.diseasetag;

import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagResponseDto;

import java.util.List;

public interface DiseaseTagRepository {

    List<DiseaseTagResponseDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType);

    List<Long> findDiseaseTagIdListByDiseaseTagNameList(List<String> diseaseTagNameList);

    List<DiseaseTag> findDiseaseTagListByDiseaseTagNameList(List<String> diseaseTagNameList);

    List<Long> saveAll(List<DiseaseTag> diseaseTagList);
}
