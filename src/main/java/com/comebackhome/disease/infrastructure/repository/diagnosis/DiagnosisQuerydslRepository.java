package com.comebackhome.disease.infrastructure.repository.diagnosis;

import com.comebackhome.common.RepositorySliceHelper;
import com.comebackhome.disease.domain.Diagnosis;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.comebackhome.disease.domain.QDiagnosis.diagnosis;
import static com.comebackhome.disease.domain.QDiagnosisDisease.diagnosisDisease;
import static com.comebackhome.disease.domain.QDisease.disease;

@Repository
@RequiredArgsConstructor
public class DiagnosisQuerydslRepository {

    private final JPAQueryFactory query;

    public Slice<Diagnosis> findDiagnosisListByLastDiagnosisIdAndUserId(Long lastDiagnosisId, Long userId, Pageable pageable) {
        List<Diagnosis> content = query.selectFrom(diagnosis)
                .leftJoin(diagnosis.diagnosisDiseaseList, diagnosisDisease).fetchJoin()
                .leftJoin(diagnosisDisease.disease, disease).fetchJoin()
                .where(diagnosis.user.id.eq(userId),
                        ltDiagnosisId(lastDiagnosisId))
                .orderBy(diagnosis.id.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        return RepositorySliceHelper.toSlice(content,pageable);


    }

    private BooleanExpression ltDiagnosisId(Long lastDiagnosisId) {
        if (lastDiagnosisId == null)
            return null;

        return diagnosis.id.lt(lastDiagnosisId);
    }
}
