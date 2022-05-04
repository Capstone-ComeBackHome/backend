package com.comebackhome.user.domain.service.dto;

import com.comebackhome.user.domain.Sex;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoSaveRequestDto {

    private int age;

    private Sex sex;

    private int height;

    private int weight;

    private String history;

    private String FamilyHistory;

    private String drugHistory;

    private String socialHistory;

    private String traumaHistory;

}
