package com.comebackhome.support.restdocs.enums;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@Builder
public class ApiResponseDto<T> {

    private T data;

    private ApiResponseDto(T data){
        this.data=data;
    }

    public static <T> ApiResponseDto<T> from(T data) {
        return new ApiResponseDto<>(data);
    }
}
