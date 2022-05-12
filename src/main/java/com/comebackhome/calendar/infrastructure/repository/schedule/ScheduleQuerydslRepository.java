package com.comebackhome.calendar.infrastructure.repository.schedule;

import com.comebackhome.calendar.domain.schedule.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static com.comebackhome.calendar.domain.diseasetag.QDiseaseTag.diseaseTag;
import static com.comebackhome.calendar.domain.schedule.QSchedule.schedule;
import static com.comebackhome.calendar.domain.schedule.QScheduleDiseaseTag.scheduleDiseaseTag;

@Repository
@RequiredArgsConstructor
public class ScheduleQuerydslRepository {

    private final JPAQueryFactory query;

    public List<Schedule> findByYearMonthAndUserId(YearMonth yearMonth, Long userId){
        return query.selectFrom(schedule).distinct()
                .leftJoin(schedule.scheduleDiseaseTagList, scheduleDiseaseTag).fetchJoin()
                .leftJoin(scheduleDiseaseTag.diseaseTag, diseaseTag).fetchJoin()
                .where(schedule.user.id.eq(userId),
                        schedule.localDate.between(yearMonth.atDay(1),yearMonth.atEndOfMonth()))
                .fetch();
    }

    Optional<Schedule> findWithScheduleDiseaseTagByIdAndUserId(Long id, Long userId){
        return Optional.ofNullable(query.selectFrom(schedule).distinct()
                .leftJoin(schedule.scheduleDiseaseTagList, scheduleDiseaseTag).fetchJoin()
                .leftJoin(scheduleDiseaseTag.diseaseTag, diseaseTag).fetchJoin()
                .where(schedule.user.id.eq(userId),
                        schedule.id.eq(id))
                .fetchOne());
    }

}
