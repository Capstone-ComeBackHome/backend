package com.comebackhome.user.application.dto;

import com.comebackhome.user.domain.Sex;
import com.comebackhome.user.domain.dto.UserEssentialUpdateDto;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEssentialUpdateRequestDto {

    private int age;

    private Sex sex;

    private int height;

    private int weight;

    public UserEssentialUpdateDto toUserEssentialUpdateDto(){
        return UserEssentialUpdateDto.builder()
                .age(age)
                .sex(sex)
                .height(height)
                .weight(weight)
                .build();
    }
}
