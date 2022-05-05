package com.comebackhome.unit.calendar.presentation;

import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDefaultTypeDiseaseTagListResponseDto;
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
        given(diseaseTagFacade.getDiseaseTagExceptCustomType()).willReturn(givenDefaultTypeDiseaseTagListResponseDto());

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
                                fieldWithPath("data.head.diseaseTypeDescription").type(STRING).description("head는 머리 태그"),
                                fieldWithPath("data.head.diseaseTagNameList").type(ARRAY).description("머리 관련 태그 리스트"),
                                fieldWithPath("data.bronchus.diseaseTypeDescription").type(STRING).description("bronchus는 기관지 태그"),
                                fieldWithPath("data.bronchus.diseaseTagNameList").type(ARRAY).description("기관지 관련 태그 리스트"),
                                fieldWithPath("data.chest.diseaseTypeDescription").type(STRING).description("chest 가슴 태그"),
                                fieldWithPath("data.chest.diseaseTagNameList").type(ARRAY).description("가슴 관련 태그 리스트"),
                                fieldWithPath("data.stomach.diseaseTypeDescription").type(STRING).description("stomach 배 태그"),
                                fieldWithPath("data.stomach.diseaseTagNameList").type(ARRAY).description("배 관련 태그 리스트"),
                                fieldWithPath("data.limb.diseaseTypeDescription").type(STRING).description("limb는 팔다리 태그"),
                                fieldWithPath("data.limb.diseaseTagNameList").type(ARRAY).description("팔다리 관련 태그 리스트"),
                                fieldWithPath("data.skin.diseaseTypeDescription").type(STRING).description("skin은 피부 태그"),
                                fieldWithPath("data.skin.diseaseTagNameList").type(ARRAY).description("피부 관련 태그 리스트")
                        ).and(successDescriptors())
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
