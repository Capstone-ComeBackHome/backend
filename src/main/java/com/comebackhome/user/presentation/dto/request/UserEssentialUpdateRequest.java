package com.comebackhome.user.presentation.dto.request;

import com.comebackhome.user.domain.Sex;
import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEssentialUpdateRequest {

    @Positive(message = "나이는 필수값입니다.")
    private int age;

    @NotNull(message = "성별은 필수값 입니다.")
    private Sex sex;

    @Positive(message = "키는 필수값입니다.")
    private int height;

    @Positive(message = "몸무게는 필수값입니다.")
    private int weight;


    public UserEssentialUpdateRequestDto toUserEssentialUpdateRequestDto(){
        return UserEssentialUpdateRequestDto.builder()
                .age(age)
                .sex(sex)
                .height(height)
                .weight(weight)
                .build();
    }

}
