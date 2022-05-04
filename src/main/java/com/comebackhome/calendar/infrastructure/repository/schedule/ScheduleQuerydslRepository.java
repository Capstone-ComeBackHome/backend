package com.comebackhome.calendar.infrastructure.repository.schedule;

import com.comebackhome.calendar.domain.Schedule;
import com.comebackhome.calendar.domain.service.dto.response.SimpleScheduleResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static com.comebackhome.calendar.domain.QSchedule.schedule;
import static com.comebackhome.calendar.domain.diseasetag.QDiseaseTag.diseaseTag;
import static com.comebackhome.calendar.domain.diseasetag.QScheduleDiseaseTag.scheduleDiseaseTag;

@Repository
@RequiredArgsConstructor
public class ScheduleQuerydslRepository {

    private final JPAQueryFactory query;

    public List<SimpleScheduleResponseDto> findByYearMonthAndUserId(YearMonth yearMonth, Long userId){
        return query.select(Projections.fields(SimpleScheduleResponseDto.class,
                schedule.id.as("scheduleId"),
                schedule.localDate,
                schedule.scheduleDiseaseTagList.size().as("diseaseTagCount")
        ))
                .from(schedule).distinct()
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
