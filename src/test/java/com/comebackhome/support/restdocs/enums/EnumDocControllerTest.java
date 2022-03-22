package com.comebackhome.support.restdocs.enums;

import com.comebackhome.support.restdocs.RestDocsTestSupport;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class EnumDocControllerTest extends RestDocsTestSupport {

    @Test
    public void 문서화에_사용할_enum_값들() throws Exception {
        ResultActions result = this.mockMvc.perform(
                get("/docs/enums")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult mvcResult = result.andReturn();
        EnumDocs enumDocs = getData(mvcResult);

        result.andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        customResponseFields("custom-response", beneathPath("data.diseaseType").withSubsectionId("diseaseType"),
                                attributes(key("title").value("diseaseType")),
                                enumConvertFieldDescriptor((enumDocs.getDiseaseType()))
                        ),
                        customResponseFields("custom-response", beneathPath("data.painType").withSubsectionId("painType"),
                                attributes(key("title").value("painType")),
                                enumConvertFieldDescriptor((enumDocs.getPainType()))
                        )
                ));
    }

    // mvc result 데이터 파싱
    private EnumDocs getData(MvcResult result) throws IOException {
        ApiResponseDto<EnumDocs> apiResponseDto = objectMapper
                .readValue(result.getResponse().getContentAsByteArray(),
                        new TypeReference<ApiResponseDto<EnumDocs>>() {}
                );
        return apiResponseDto.getData();
    }

    // 커스텀 템플릿 사용을 위한 함수
    public static CustomResponseFieldsSnippet customResponseFields
                                (String type,
                                 PayloadSubsectionExtractor<?> subsectionExtractor,
                                 Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes
                , true);
    }

    private static FieldDescriptor[] enumConvertFieldDescriptor(Map<String, String> enumValues) {
        return enumValues.entrySet().stream()
                .map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
                .toArray(FieldDescriptor[]::new);
    }

}
