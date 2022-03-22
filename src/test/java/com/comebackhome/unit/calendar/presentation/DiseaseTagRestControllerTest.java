package com.comebackhome.unit.calendar.presentation;

import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagResponseDto;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseTagRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/diseaseTags";
    private final String ACCESS_TOKEN = "Bearer accessToken";

    @Test
    @WithMockUser(roles = "USER")
    void CustomType을_제외한_diseaseTag_가져오기() throws Exception{
        // given
        given(diseaseTagQueryUseCase.getDiseaseTagExceptCustomType()).willReturn(givenDiseaseTagResponseDto());

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        responseFields(
                                fieldWithPath("headDiseaseTagList").type(ARRAY).description("머리 관련 태그 리스트"),
                                fieldWithPath("headDiseaseTagList[0].diseaseType").type(STRING).description("태그 타입"),
                                fieldWithPath("headDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("bronchusDiseaseTagList").type(ARRAY).description("기관지 관련 태그 리스트"),
                                fieldWithPath("bronchusDiseaseTagList[0].diseaseType").type(STRING).description("태그 타입"),
                                fieldWithPath("bronchusDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("chestDiseaseTagList").type(ARRAY).description("가슴 관련 태그 리스트"),
                                fieldWithPath("chestDiseaseTagList[0].diseaseType").type(STRING).description("태그 타입"),
                                fieldWithPath("chestDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("stomachDiseaseTagList").type(ARRAY).description("배 관련 태그 리스트"),
                                fieldWithPath("stomachDiseaseTagList[0].diseaseType").type(STRING).description("태그 타입"),
                                fieldWithPath("stomachDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("limbDiseaseTagList").type(ARRAY).description("팔다리 관련 태그 리스트"),
                                fieldWithPath("limbDiseaseTagList[0].diseaseType").type(STRING).description("태그 타입"),
                                fieldWithPath("limbDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("skinDiseaseTagList").type(ARRAY).description("피부 관련 태그 리스트"),
                                fieldWithPath("skinDiseaseTagList[0].diseaseType").type(STRING).description("태그 타입"),
                                fieldWithPath("skinDiseaseTagList[0].name").type(STRING).description("태그 이름")
                        )
                ))
                ;
    }

    @Test
    void 토큰_없이_접근_시도_실패() throws Exception{
        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                ;
    }


}
