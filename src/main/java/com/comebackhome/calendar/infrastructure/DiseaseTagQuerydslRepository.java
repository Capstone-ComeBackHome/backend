package com.comebackhome.calendar.infrastructure;

import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.domain.dto.DiseaseTagQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.comebackhome.calendar.domain.QDiseaseTag.diseaseTag;

@Repository
@RequiredArgsConstructor
public class DiseaseTagQuerydslRepository {

    private final JPAQueryFactory query;

    public List<DiseaseTagQueryDto> findAllDiseaseTagExceptDiseaseType(DiseaseType diseaseType){
        return query.select(Projections.fields(DiseaseTagQueryDto.class,
                diseaseTag.diseaseType,
                diseaseTag.name
        ))
                .from(diseaseTag)
                .where(diseaseTag.diseaseType.ne(diseaseType))
                .fetch();

    }
}
