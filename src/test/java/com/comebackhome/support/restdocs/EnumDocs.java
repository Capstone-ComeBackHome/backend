package com.comebackhome.support.restdocs;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumDocs {

    // 문서화하고 싶은 모든 enum값을 명시
    Map<String, String> diseaseType;
    Map<String, String> painType;

}
