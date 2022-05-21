package com.comebackhome.calendar.infrastructure.repository.schedulediseasetag;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.repository.dto.BubbleQueryDto;
import com.comebackhome.calendar.domain.schedule.repository.dto.LineQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.comebackhome.calendar.domain.diseasetag.QDiseaseTag.diseaseTag;
import static com.comebackhome.calendar.domain.schedule.QSchedule.schedule;
import static com.comebackhome.calendar.domain.schedule.QScheduleDiseaseTag.scheduleDiseaseTag;

@Repository
@RequiredArgsConstructor
public class ScheduleDiseaseTagQuerydslRepository {

    private final JPAQueryFactory query;

    public List<BubbleQueryDto> findBubbleQueryDtoByUserIdWithinAMonthExceptCustomType(Long userId) {
        return query.select(Projections.fields(BubbleQueryDto.class,
                scheduleDiseaseTag.schedule.painType,
                scheduleDiseaseTag.diseaseTag.diseaseType
        )).from(scheduleDiseaseTag)
                .join(scheduleDiseaseTag.schedule,schedule)
                .join(scheduleDiseaseTag.diseaseTag, diseaseTag)
                .where(scheduleDiseaseTag.schedule.localDate.after(LocalDate.now().minusMonths(1).minusDays(1)),
                        scheduleDiseaseTag.schedule.user.id.eq(userId),
                        scheduleDiseaseTag.diseaseTag.diseaseType.ne(DiseaseType.CUSTOM)
                        )
                .fetch();
    }

    public List<LineQueryDto> findLineQueryDtoByUserIdWithinThreeMonthExceptCustomType(Long userId) {
        return query.select(Projections.fields(LineQueryDto.class,
                scheduleDiseaseTag.schedule.localDate.as("scheduleDate"),
                scheduleDiseaseTag.schedule.painType,
                scheduleDiseaseTag.diseaseTag.name.as("diseaseName")
                ))
                .from(scheduleDiseaseTag)
                .join(scheduleDiseaseTag.schedule,schedule)
                .join(scheduleDiseaseTag.diseaseTag, diseaseTag)
                .where(scheduleDiseaseTag.schedule.localDate.after(LocalDate.now().minusMonths(3).minusDays(1)),
                        scheduleDiseaseTag.schedule.user.id.eq(userId),
                        scheduleDiseaseTag.diseaseTag.diseaseType.ne(DiseaseType.CUSTOM)
                )
                .fetch();
    }
}
