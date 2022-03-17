package com.comebackhome.disease.infrastructure.repository;

import com.comebackhome.disease.domain.dto.SimpleDiseaseQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.comebackhome.disease.domain.QDisease.disease;

@Repository
@RequiredArgsConstructor
public class DiseaseQuerydslRepository {

    private final JPAQueryFactory query;

    public Optional<SimpleDiseaseQueryDto> findDiseaseSimpleQueryDtoByName(String diseaseName){

        // diseaseName이 index이므로 커버링 인덱스 방식 사용
        Long id = query.select(disease.id)
                .from(disease)
                .where(disease.name.eq(diseaseName))
                .fetchOne();

        if (id == null)
            return Optional.empty();

        return Optional.ofNullable(query.select(Projections.fields(SimpleDiseaseQueryDto.class,
                disease.name,
                disease.definition,
                disease.recommendDepartment
                ))
                .from(disease)
                .where(disease.id.eq(id))
                .fetchOne());
    }
}
