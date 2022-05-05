package com.comebackhome.common;

import lombok.*;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private Result result;
    private T data;

    private String message;
    private String code;
    @Builder.Default
    private List<FieldError> errors = new ArrayList<>();

    private CommonResponse(String message, String code) {
        this(message,code,null);
    }

    private CommonResponse(String message, String code, List<FieldError> errors) {
        this.result = Result.FAIL;
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public static CommonResponse errorOf(String message, String code) {
        return new CommonResponse(message, code);
    }

    public static CommonResponse errorOf(String message, String code, BindingResult result) {
        return new CommonResponse(message, code, FieldError.of(result));
    }

    public static CommonResponse success() {
        return CommonResponse.builder()
                .result(Result.SUCCESS)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return (CommonResponse<T>) CommonResponse.builder()
                .result(Result.SUCCESS)
                .data(data)
                .build();
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class FieldError {
        private String field;

        private String value;

        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }


        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(toList());
        }
    }

    public enum Result {
        SUCCESS, FAIL
    }

}

