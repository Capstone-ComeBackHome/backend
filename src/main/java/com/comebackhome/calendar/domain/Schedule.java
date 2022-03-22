package com.comebackhome.calendar.domain;

import com.comebackhome.calendar.application.dto.ScheduleSaveRequestDto;
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
    private PainType painType;

    public static Schedule from(ScheduleSaveRequestDto scheduleSaveRequestDto){
        return Schedule.builder()
                .localDate(scheduleSaveRequestDto.getLocalDate())
                .dailyNote(scheduleSaveRequestDto.getDailyNote())
                .painType(scheduleSaveRequestDto.getPainType())
                .user(User.builder().id(scheduleSaveRequestDto.getUserId()).build())
                .build();
    }

}
