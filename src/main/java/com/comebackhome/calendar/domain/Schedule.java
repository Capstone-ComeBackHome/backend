package com.comebackhome.calendar.domain;

import com.comebackhome.common.domain.BaseEntity;
import com.comebackhome.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @OneToMany(mappedBy = "schedule")
    @Builder.Default
    private List<ScheduleDiseaseTag> scheduleDiseaseTagList = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate localDate;

    private String dailyNote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PainType painType;

    public void updateDailyNote(String dailyNote){
        this.dailyNote = dailyNote;
    }

    public void updatePainType(PainType painType){
        this.painType = painType;
    }

}
