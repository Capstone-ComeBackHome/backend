package com.comebackhome.user.presentation.dto.request;

import com.comebackhome.user.application.dto.UserInfoSaveRequestDto;
import com.comebackhome.user.domain.Sex;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoSaveRequest {

    @Positive(message = "나이는 필수값입니다.")
    private int age;

    @NotNull(message = "성별은 필수값입니다.")
    private Sex sex;

    @Positive(message = "키는 필수값입니다.")
    private int height;

    @Positive(message = "몸무게는 필수값입니다.")
    private int weight;

    private String history;

    private String FamilyHistory;

    private String drugHistory;

    private String socialHistory;

    private String traumaHistory;

    public UserInfoSaveRequestDto toUserInfoSaveRequestDto(){
        return UserInfoSaveRequestDto.builder()
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
