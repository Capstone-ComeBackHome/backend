package com.comebackhome.calendar.domain.schedule;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleDiseaseTag {

    @Id
    @TableGenerator(
            name = "SCHEDULE_DISEASE_TAG_GENERATOR",
            table = "SCHEDULE_DISEASE_TAG_SEQUENCE",
            pkColumnName = "SCHEDULE_DISEASE_TAG_SEQ"
    )
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "SCHEDULE_DISEASE_TAG_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISEASE_TAG_ID", nullable = false)
    private DiseaseTag diseaseTag;

    public static ScheduleDiseaseTag of(Long scheduleId, Long diseaseTagId){
        return ScheduleDiseaseTag.builder()
                .schedule(Schedule.builder().id(scheduleId).build())
                .diseaseTag(DiseaseTag.builder().id(diseaseTagId).build())
                .build();
    }
}
