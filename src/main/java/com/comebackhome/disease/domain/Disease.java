package com.comebackhome.disease.domain;

import com.comebackhome.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Disease extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Lob
    @Column(nullable = false)
    private String definition;

    @Column(nullable = false)
    private String recommendDepartment;

    @Lob
    @Column(nullable = false)
    private String symptom;

    @Lob
    @Column(nullable = false)
    private String cause;

    @Lob
    @Column(nullable = false)
    private String hospitalCare;


}
