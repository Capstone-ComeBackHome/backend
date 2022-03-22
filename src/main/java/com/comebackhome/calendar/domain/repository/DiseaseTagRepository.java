package com.comebackhome.calendar.domain.repository;

import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;

import java.util.List;

public interface DiseaseTagRepository {

    List<DiseaseTagQueryDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType);

    List<Long> findDiseaseTagIdListByDiseaseTagNameList(List<String> diseaseTagNameList);

    List<DiseaseTag> findDiseaseTagListByDiseaseTagNameList(List<String> diseaseTagNameList);

    List<Long> saveAll(List<DiseaseTag> diseaseTagList);
}
