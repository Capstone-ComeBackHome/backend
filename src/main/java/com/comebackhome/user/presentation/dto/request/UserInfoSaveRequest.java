package com.comebackhome.user.presentation.dto.request;

import com.comebackhome.user.domain.Sex;
import com.comebackhome.user.domain.service.dto.UserInfoSaveRequestDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String history;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String FamilyHistory;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String drugHistory;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
    private String socialHistory;

    @Size(max = 50,message = "50자 이내로 입력하세요.")
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
