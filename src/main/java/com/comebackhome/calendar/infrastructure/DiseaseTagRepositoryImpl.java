package com.comebackhome.calendar.infrastructure;

import com.comebackhome.calendar.domain.DiseaseTagRepository;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiseaseTagRepositoryImpl implements DiseaseTagRepository {

    private final DiseaseTagQuerydslRepository diseaseTagQuerydslRepository;

    @Override
    public List<DiseaseTagQueryDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType) {
        return diseaseTagQuerydslRepository.findAllDiseaseTagExceptDiseaseType(diseaseType);
    }
}
