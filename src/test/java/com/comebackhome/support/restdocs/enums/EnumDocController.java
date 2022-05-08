package com.comebackhome.support.restdocs.enums;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.PainType;
import com.comebackhome.user.domain.AuthProvider;
import com.comebackhome.user.domain.Sex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/docs")
public class EnumDocController {

    @GetMapping("/enums")
    public ApiResponseDto<EnumDocs> findEnums() {

        // 문서화 하고 싶은 -> EnumDocs 클래스에 담긴 모든 Enum 값 생성
        Map<String, String> diseaseType = Arrays.stream(DiseaseType.values()).collect(Collectors.toMap(DiseaseType::name,DiseaseType::getDescription));
        Map<String, String> painType = Arrays.stream(PainType.values()).collect(Collectors.toMap(PainType::name,PainType::getDescription));
        Map<String, String> authProvider = Arrays.stream(AuthProvider.values()).collect(Collectors.toMap(AuthProvider::name,AuthProvider::getDescription));
        Map<String, String> sex = Arrays.stream(Sex.values()).collect(Collectors.toMap(Sex::name,Sex::getDescription));

        // 전부 담아서 반환 -> 테스트에서는 이걸 꺼내 해석하여 조각을 만들면 된다.
        return ApiResponseDto.from(EnumDocs.builder()
                .diseaseType(diseaseType)
                .painType(painType)
                .authProvider(authProvider)
                .sex(sex)
                .build()
        );
    }
}
