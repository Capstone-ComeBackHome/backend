package com.comebackhome.user.application.dto;

import com.comebackhome.user.domain.Sex;
import com.comebackhome.user.domain.dto.UserInfoDto;
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

    public UserInfoDto toUserInfoDto(){
        return UserInfoDto.builder()
                .age(age)
                .sex(sex)
                .height(height)
                .weight(weight)
                .history(history)
                .FamilyHistory(FamilyHistory)
                .drugHistory(drugHistory)
                .socialHistory(socialHistory)
                .traumaHistory(traumaHistory)
                .build();
    }
}
