package com.comebackhome.unit.calendar.presentation;

import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.presentation.dto.ScheduleSaveRequest;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static com.comebackhome.config.RestDocsConfig.field;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTagRequest;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenScheduleSaveRequest;
import static com.comebackhome.support.restdocs.DocumentLinkGenerator.DocUrl.DISEASE_TYPE;
import static com.comebackhome.support.restdocs.DocumentLinkGenerator.DocUrl.PAIN_TYPE;
import static com.comebackhome.support.restdocs.DocumentLinkGenerator.generateLinkCode;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CalendarRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/calendars";
    private final String ACCESS_TOKEN = "Bearer accessToken";


    @Test
    @WithMockUser(roles = "USER")
    void 스케줄_저장하기_성공() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestFields(
                                fieldWithPath("diseaseTagRequestList").type(ARRAY).description("질병 태그 리스트").attributes(field("constraints", "최소 1개, 최대 5개")),
                                fieldWithPath("diseaseTagRequestList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("diseaseTagRequestList[0].name").type(STRING).description("질병 태그 이름"),
                                fieldWithPath("dailyNote").type(STRING).description("하루 일기").optional(),
                                fieldWithPath("painType").type(STRING).description(generateLinkCode(PAIN_TYPE)).optional(),
                                fieldWithPath("localDate").type(STRING).description("스케줄 날짜")
                        )
                ));
                ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 날짜_없이_스케줄_저장하기_실패() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setLocalDate(null);
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 증상_없이_스케줄_저장하기_실패() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setDiseaseTagRequestList(null);
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 증상_5개_초과_스케줄_저장하기_실패() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setDiseaseTagRequestList(
                List.of(givenDiseaseTagRequest(DiseaseType.CUSTOM,"test1"),
                        givenDiseaseTagRequest(DiseaseType.CUSTOM,"test2"),
                        givenDiseaseTagRequest(DiseaseType.CUSTOM,"test3"),
                        givenDiseaseTagRequest(DiseaseType.CUSTOM,"test4"),
                        givenDiseaseTagRequest(DiseaseType.CUSTOM,"test5"),
                        givenDiseaseTagRequest(DiseaseType.CUSTOM,"test6"))
        );
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ));
        ;
    }

    @Test
    void 토큰_없이_스케줄_저장하기_실패() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .content(createJson(scheduleSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

}
