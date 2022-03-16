package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.infrastructure.repository.dto.CauseQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.comebackhome.disease.domain.QCause.cause;

@Repository
@RequiredArgsConstructor
public class CauseQuerydslRepository {

    private final JPAQueryFactory query;

    public List<CauseQueryDto> findCauseQueryDtoByDiseaseId(Long diseaseId){
        return query.select(Projections.fields(CauseQueryDto.class,
                    cause.reason
                ))
                .from(cause)
                .where(cause.disease.id.eq(diseaseId))
                .fetch();

    }
}
