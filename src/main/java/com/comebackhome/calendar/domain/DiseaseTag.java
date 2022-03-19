package com.comebackhome.calendar.domain;

import com.comebackhome.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiseaseTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiseaseType diseaseType;

    @Column(nullable = false,unique = true)
    private String name;
}
