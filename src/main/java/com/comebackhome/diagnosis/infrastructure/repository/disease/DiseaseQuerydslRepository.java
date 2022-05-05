package com.comebackhome.diagnosis.infrastructure.repository.disease;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.comebackhome.diagnosis.domain.disease.QDisease.disease;


@Repository
@RequiredArgsConstructor
public class DiseaseQuerydslRepository {

    private final JPAQueryFactory query;

    public Optional<SimpleDiseaseResponseDto> findDiseaseSimpleResponseDtoByName(String diseaseName){

        // diseaseName이 index이므로 커버링 인덱스 방식 사용
        Long id = query.select(disease.id)
                .from(disease)
                .where(disease.name.eq(diseaseName))
                .fetchOne();

        if (id == null)
            return Optional.empty();

        return Optional.ofNullable(query.select(Projections.fields(SimpleDiseaseResponseDto.class,
                disease.id.as("diseaseId"),
                disease.name,
                disease.definition,
                disease.recommendDepartment
                ))
                .from(disease)
                .where(disease.id.eq(id))
                .fetchOne());
    }
}
