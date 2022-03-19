package com.comebackhome.calendar.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleDiseaseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISEASE_TAG_ID", nullable = false)
    private DiseaseTag diseaseTag;
}
