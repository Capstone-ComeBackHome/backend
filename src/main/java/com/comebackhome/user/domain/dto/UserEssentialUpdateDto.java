package com.comebackhome.user.domain.dto;

import com.comebackhome.user.domain.Sex;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEssentialUpdateDto {

    private int age;

    private Sex sex;

    private int height;

    private int weight;
}
