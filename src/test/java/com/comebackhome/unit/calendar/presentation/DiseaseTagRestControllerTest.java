package com.comebackhome.unit.calendar.presentation;

import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagListResponseDto;
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
        given(diseaseTagFacade.getDiseaseTagExceptCustomType()).willReturn(givenDiseaseTagListResponseDto());

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
                                fieldWithPath("data.headDiseaseTagList").type(ARRAY).description("머리 관련 태그 리스트"),
                                fieldWithPath("data.headDiseaseTagList[0].diseaseType").type(STRING).description("머리 관련 태그 타입은 HEAD"),
                                fieldWithPath("data.headDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("data.bronchusDiseaseTagList").type(ARRAY).description("기관지 관련 태그 리스트"),
                                fieldWithPath("data.bronchusDiseaseTagList[0].diseaseType").type(STRING).description("기관지 관련 태그 타입은 BRONCHUS"),
                                fieldWithPath("data.bronchusDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("data.chestDiseaseTagList").type(ARRAY).description("가슴 관련 태그 리스트"),
                                fieldWithPath("data.chestDiseaseTagList[0].diseaseType").type(STRING).description("가슴 관련 태그 타입은 CHEST"),
                                fieldWithPath("data.chestDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("data.stomachDiseaseTagList").type(ARRAY).description("배 관련 태그 리스트"),
                                fieldWithPath("data.stomachDiseaseTagList[0].diseaseType").type(STRING).description("배 관련 태그 타입은 STOMACH"),
                                fieldWithPath("data.stomachDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("data.limbDiseaseTagList").type(ARRAY).description("팔다리 관련 태그 리스트"),
                                fieldWithPath("data.limbDiseaseTagList[0].diseaseType").type(STRING).description("팔다리 관련 태그 타입은 LIMB"),
                                fieldWithPath("data.limbDiseaseTagList[0].name").type(STRING).description("태그 이름"),
                                fieldWithPath("data.skinDiseaseTagList").type(ARRAY).description("피부 관련 태그 리스트"),
                                fieldWithPath("data.skinDiseaseTagList[0].diseaseType").type(STRING).description("피부 관련 태그 타입은 SKIN"),
                                fieldWithPath("data.skinDiseaseTagList[0].name").type(STRING).description("태그 이름")
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
