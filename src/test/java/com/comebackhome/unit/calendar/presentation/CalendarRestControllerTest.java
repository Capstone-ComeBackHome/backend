package com.comebackhome.unit.calendar.presentation;

import com.comebackhome.calendar.domain.diseasetag.DiseaseType;
import com.comebackhome.calendar.domain.schedule.service.dto.response.ScheduleResponseDto;
import com.comebackhome.calendar.presentation.dto.request.ScheduleModifyRequest;
import com.comebackhome.calendar.presentation.dto.request.ScheduleSaveRequest;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.List;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.CUSTOM;
import static com.comebackhome.config.RestDocsConfig.field;
import static com.comebackhome.support.helper.CalendarGivenHelper.*;
import static com.comebackhome.support.restdocs.enums.DocumentLinkGenerator.DocUrl.DISEASE_TYPE;
import static com.comebackhome.support.restdocs.enums.DocumentLinkGenerator.DocUrl.PAIN_TYPE;
import static com.comebackhome.support.restdocs.enums.DocumentLinkGenerator.generateLinkCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CalendarRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/calendars";
    private final String ACCESS_TOKEN = "Bearer accessToken";


    @Test
    @WithMockUser(roles = "USER")
    void ?????????_????????????_??????() throws Exception{
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
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        requestFields(
                                fieldWithPath("diseaseTagRequestList").type(ARRAY).description("?????? ?????? ?????????").attributes(field("constraints", "?????? 1???, ?????? 5???")),
                                fieldWithPath("diseaseTagRequestList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("diseaseTagRequestList[0].name").type(STRING).description("?????? ?????? ??????"),
                                fieldWithPath("dailyNote").type(STRING).description("?????? ??????").optional(),
                                fieldWithPath("painType").type(STRING).description(generateLinkCode(PAIN_TYPE)),
                                fieldWithPath("scheduleDate").type(STRING).description("????????? ??????")
                        ),
                        responseFields(
                                voidSuccessDescriptors()
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_??????_?????????_????????????_??????() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setScheduleDate(null);
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
    void ??????_??????_?????????_????????????_??????() throws Exception{
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
    void ??????_??????_??????_?????????_????????????_??????() throws Exception{
        // given
        ScheduleSaveRequest scheduleSaveRequest = givenScheduleSaveRequest();
        scheduleSaveRequest.setPainType(null);
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
    void ??????_5???_??????_?????????_????????????_??????() throws Exception{
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
    void ??????_??????_?????????_????????????_??????() throws Exception{
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

    @Test
    @WithMockUser(roles = "USER")
    void ?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        pathParameters(
                                parameterWithName("scheduleId").description("????????? ????????? ID")
                        ),
                        responseFields(
                                voidSuccessDescriptors()
                        )
                ))
                ;
    }

    @Test
    void ??????_??????_?????????_??????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    void ?????????_id_??????_?????????_??????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{scheduleId}", "")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ???_?????????_???_????????????_??????_scheduleId???_??????_??????() throws Exception{
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new ScheduleNotFoundException()).given(calendarFacade).deleteSchedule(any(),any());

        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{scheduleId}", "1")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_??????_?????????_?????????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        List<ScheduleResponseDto> scheduleResponseDtoList = List.of(
                givenScheduleResponseDto(1L,LocalDate.of(2022,5,1)),
                givenScheduleResponseDto(2L,LocalDate.of(2022,5,2))
        );

        given(calendarFacade.getMyMonthSchedule(any(),any())).willReturn(scheduleResponseDtoList);


        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?yearMonth=2022-03")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        requestParameters(
                                parameterWithName("yearMonth").description("yyyy-MM ????????? ???, ???")
                        ),
                        responseFields(
                                fieldWithPath("data.scheduleResponseList[0].scheduleId").type(NUMBER).description("????????? ID"),
                                fieldWithPath("data.scheduleResponseList[0].scheduleDate").type(STRING).description("?????? ??????"),
                                fieldWithPath("data.scheduleResponseList[0].dailyNote").type(STRING).description("??????").optional(),
                                fieldWithPath("data.scheduleResponseList[0].painType").type(STRING).description(generateLinkCode(PAIN_TYPE)),
                                fieldWithPath("data.scheduleResponseList[0].diseaseTagResponseList").type(ARRAY).description("?????? ?????????"),
                                fieldWithPath("data.scheduleResponseList[0].diseaseTagResponseList[0].diseaseType").type(STRING).description("?????? ?????? ??????"),
                                fieldWithPath("data.scheduleResponseList[0].diseaseTagResponseList[0].name").type(STRING).description("?????????"),
                                fieldWithPath("data.scheduleResponseList[0].diseaseTagResponseList[1].diseaseType").type(STRING).description("?????? ?????? ??????"),
                                fieldWithPath("data.scheduleResponseList[0].diseaseTagResponseList[1].name").type(STRING).description("?????????")
                                ).and(successDescriptors())
                ))
        ;
    }

    @Test
    void yearMonth_??????_???_?????????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    void ??????_??????_???_?????????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?yearMonth=2022-03")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void scheduleId???_?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        ScheduleResponseDto scheduleResponseDto = givenScheduleResponseDto();
        given(calendarFacade.getMySchedule(any(),any())).willReturn(scheduleResponseDto);


        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        pathParameters(
                                parameterWithName("scheduleId").description("????????? ????????? ID")
                        ),
                        responseFields(
                                fieldWithPath("data.scheduleId").type(NUMBER).description("????????? Id"),
                                fieldWithPath("data.scheduleDate").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.diseaseTagResponseList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("data.diseaseTagResponseList[0].name").type(STRING).description("?????? ??????"),
                                fieldWithPath("data.dailyNote").type(STRING).description("?????? ??????").optional(),
                                fieldWithPath("data.painType").type(STRING).description(generateLinkCode(PAIN_TYPE)).optional()
                        ).and(successDescriptors())
                ))
        ;
    }

    @Test
    void ??????_??????_scheduleId???_?????????_??????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/{scheduleId}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    void ?????????_id_??????_?????????_??????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/{scheduleId}","")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ?????????_?????????_???_????????????_??????_scheduleId???_?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new ScheduleNotFoundException()).given(calendarFacade).getMySchedule(any(),any());

        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/{scheduleId}",-1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        pathParameters(
                                parameterWithName("scheduleId").description("????????? ????????? ID")
                        ),
                        requestFields(
                                fieldWithPath("diseaseTagRequestList").type(ARRAY).description("?????? ?????? ?????????"),
                                fieldWithPath("diseaseTagRequestList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("diseaseTagRequestList[0].name").type(STRING).description("?????? ??????"),
                                fieldWithPath("dailyNote").type(STRING).description("?????? ??????").optional(),
                                fieldWithPath("painType").type(STRING).description("?????? ??????")
                        ),
                        responseFields(
                                voidSuccessDescriptors()
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_??????_??????_?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setPainType(null);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ))
        ;
    }

    @Test
    void ??????_??????_?????????_??????() throws Exception{
        // given
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();

        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}",1)
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    void ?????????_id_??????_?????????_??????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}","")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_?????????_??????_?????????_6???_?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(
                List.of(
                        givenDiseaseTagRequest(CUSTOM,"test1"),
                        givenDiseaseTagRequest(CUSTOM,"test2"),
                        givenDiseaseTagRequest(CUSTOM,"test3"),
                        givenDiseaseTagRequest(CUSTOM,"test4"),
                        givenDiseaseTagRequest(CUSTOM,"test5"),
                        givenDiseaseTagRequest(CUSTOM,"test6")
                ));

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_?????????_??????_?????????_0???_?????????_??????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        scheduleModifyRequest.setDiseaseTagRequestList(null);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ?????????_?????????_???_????????????_??????_scheduleId???_?????????_??????() throws Exception{
        // given
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new ScheduleNotFoundException()).given(calendarFacade).modifyMySchedule(any(),any(),any());

        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/{scheduleId}",-1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(scheduleModifyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_1?????????_bubble_?????????_?????????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        given(calendarFacade.getBubbleStatisticData(any())).willReturn(givenBubbleResponseDtoList());

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/statistics/bubble")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        responseFields(
                                fieldWithPath("data.bubbleResponseList").type(ARRAY).description("?????? ????????? ?????????"),
                                fieldWithPath("data.bubbleResponseList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("data.bubbleResponseList[0].count").type(NUMBER).description("?????? ?????????"),
                                fieldWithPath("data.bubbleResponseList[0].painAverage").type(NUMBER).description("?????? ?????? ??????")
                        ).and(successDescriptors())
                ))
        ;
    }

    @Test
    void ??????_??????_bubble_?????????_?????????_??????() throws Exception{

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/statistics/bubble")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
    }

    @Test
    @WithMockUser(roles = "USER")
    void ??????_3?????????_line_?????????_?????????_??????() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        given(calendarFacade.getLineStatisticDate(any())).willReturn(givenLineResponseDto());

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/statistics/line")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer ?????? Access Token")
                        ),
                        responseFields(
                                fieldWithPath("data.top1").type(ARRAY).description("?????? ????????? ?????? ?????? ????????? ?????????"),
                                fieldWithPath("data.top1[0].scheduleDate").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.top1[0].painType").type(STRING).description(generateLinkCode(PAIN_TYPE)),
                                fieldWithPath("data.top1[0].diseaseName").type(STRING).description("?????????"),

                                fieldWithPath("data.top2").type(ARRAY).description("????????? ???????????? ?????? ?????? ????????? ?????????"),
                                fieldWithPath("data.top2[0].scheduleDate").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.top2[0].painType").type(STRING).description(generateLinkCode(PAIN_TYPE)),
                                fieldWithPath("data.top2[0].diseaseName").type(STRING).description("?????????"),

                                fieldWithPath("data.top3").type(ARRAY).description("????????? ???????????? ?????? ?????? ????????? ?????????"),
                                fieldWithPath("data.top3[0].scheduleDate").type(STRING).description("????????? ??????"),
                                fieldWithPath("data.top3[0].painType").type(STRING).description(generateLinkCode(PAIN_TYPE)),
                                fieldWithPath("data.top3[0].diseaseName").type(STRING).description("?????????"),

                                fieldWithPath("data.before3MonthDate").type(STRING).description("3?????? ??? ??????")

                        ).and(successDescriptors())
                ))
        ;
    }

    @Test
    void ??????_??????_line_?????????_?????????_??????() throws Exception{

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/statistics/line")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
    }

}
