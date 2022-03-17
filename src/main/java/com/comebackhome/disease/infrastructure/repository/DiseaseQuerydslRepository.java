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
        return Optional.ofNullable(query.select(Projections.fields(SimpleDiseaseQueryDto.class,
                disease.name,
                disease.definition,
                disease.recommendDepartment
                ))
                .from(disease)
                .where(disease.name.eq(diseaseName))
                .fetchOne());
    }
}
