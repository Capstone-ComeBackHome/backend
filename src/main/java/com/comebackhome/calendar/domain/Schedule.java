package com.comebackhome.calendar.domain;

import com.comebackhome.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "schedule")
    @Builder.Default
    private List<ScheduleDiseaseTag> scheduleDiseaseTagList = new ArrayList<>();

    // todo serializer 필요할듯
    @Column(nullable = false)
    private LocalDate localDate;

    private String dailyNote;

    @Enumerated(EnumType.STRING)
    private PainType painType;

}
