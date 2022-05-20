package com.comebackhome.diagnosis.infrastructure.repository.disease;

import com.comebackhome.diagnosis.domain.disease.service.dto.response.SimpleDiseaseResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.comebackhome.diagnosis.domain.disease.QDisease.disease;


@Repository
@RequiredArgsConstructor
public class DiseaseQuerydslRepository {

    private final JPAQueryFactory query;

    // 쿼리 최적화 커버링 인덱스로 빠르게 땡겨오고 필요한 필드만 dto로 조회함과 동시에 이미 알고 있는 필드는 as 표현식
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
                Expressions.asString(diseaseName).as("name"),
                disease.definition,
                disease.recommendDepartment
                ))
                .from(disease)
                .where(disease.id.eq(id))
                .fetchOne());
    }
}
