package com.comebackhome.user.domain.service.dto;

import com.comebackhome.user.domain.Sex;
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

}
