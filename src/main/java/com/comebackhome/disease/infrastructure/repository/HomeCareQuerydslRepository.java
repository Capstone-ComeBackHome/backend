package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.infrastructure.repository.dto.HomeCareQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.comebackhome.disease.domain.QHomeCare.homeCare;

@Repository
@RequiredArgsConstructor
public class HomeCareQuerydslRepository {

    private final JPAQueryFactory query;

    public List<HomeCareQueryDto> findHomeCareByDiseaseId(Long diseaseId){
        return query.select(Projections.fields(HomeCareQueryDto.class,
                homeCare.solution
                ))
                .from(homeCare)
                .where(homeCare.disease.id.eq(diseaseId))
                .fetch();
    }
}
