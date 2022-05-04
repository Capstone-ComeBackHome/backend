package com.comebackhome.calendar.domain.diseasetag;

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
    @TableGenerator(
            name = "DISEASE_TAG_SEQ_GENERATOR",
            table = "DISEASE_TAG_SEQUENCE",
            pkColumnName = "DISEASE_TAG_SEQ",
            initialValue = 60
    )
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "DISEASE_TAG_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiseaseType diseaseType;

    @Column(nullable = false,unique = true)
    private String name;

    public static DiseaseTag of(DiseaseType diseaseType, String name){
        return DiseaseTag.builder()
                .diseaseType(diseaseType)
                .name(name)
                .build();
    }
}
