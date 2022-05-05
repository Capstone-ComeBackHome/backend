package com.comebackhome.calendar.domain.diseasetag.repository;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.diseasetag.service.dto.DiseaseTagQueryDto;

import java.util.List;

public interface DiseaseTagRepository {

    List<DiseaseTagQueryDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType);

    List<Long> findDiseaseTagIdListByDiseaseTagNameList(List<String> diseaseTagNameList);

    List<DiseaseTag> findDiseaseTagListByDiseaseTagNameList(List<String> diseaseTagNameList);

    List<Long> saveAll(List<DiseaseTag> diseaseTagList);
}
