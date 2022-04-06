package com.comebackhome.disease.domain;


import com.comebackhome.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiagnosisDisease extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISEASE_ID", nullable = false)
    private Disease disease;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIAGNOSIS_ID", nullable = false)
    private Diagnosis diagnosis;

    public static DiagnosisDisease of(Long diseaseId, Long diagnosisId){
        return DiagnosisDisease.builder()
                .disease(Disease.builder().id(diseaseId).build())
                .diagnosis(Diagnosis.builder().id(diagnosisId).build())
                .build();
    }
}
