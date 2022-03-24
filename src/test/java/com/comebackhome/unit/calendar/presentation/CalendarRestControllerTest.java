package com.comebackhome.unit.calendar.presentation;

import com.comebackhome.calendar.application.dto.ScheduleResponseDto;
import com.comebackhome.calendar.application.dto.SimpleScheduleResponseDto;
import com.comebackhome.calendar.domain.DiseaseType;
import com.comebackhome.calendar.presentation.dto.ScheduleModifyRequest;
import com.comebackhome.calendar.presentation.dto.ScheduleSaveRequest;
import com.comebackhome.common.exception.schedule.ScheduleNotFoundException;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.List;

import static com.comebackhome.calendar.domain.DiseaseType.CUSTOM;
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

    @Test
    @WithMockUser(roles = "USER")
    void 스케줄_삭제_성공() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        pathParameters(
                                parameterWithName("scheduleId").description("삭제할 스케줄 ID")
                        )
                ))
                ;
    }

    @Test
    void 토근_없이_스케줄_삭제_실패() throws Exception{
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
    void 스케줄_id_없이_스케줄_삭제_실패() throws Exception{
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
    void 내_스케줄_중_존재하지_않는_scheduleId안_경우_실패() throws Exception{
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new ScheduleNotFoundException()).given(calendarCommandUseCase).deleteSchedule(any(),any());

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
    void 특정_월의_자신의_스케줄_조회() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        List<SimpleScheduleResponseDto> simpleScheduleResponseDtoList = List.of(
                givenSimpleScheduleResponseDto(1L, LocalDate.of(2022, 3, 1), 1),
                givenSimpleScheduleResponseDto(2L, LocalDate.of(2022, 3, 2), 2),
                givenSimpleScheduleResponseDto(3L, LocalDate.of(2022, 3, 3), 3)
        );
        given(calendarQueryUseCase.getMyMonthSchedule(any(),any())).willReturn(simpleScheduleResponseDtoList);


        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?yearMonth=2022-03")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestParameters(
                                parameterWithName("yearMonth").description("yyyy-MM 형식의 년, 월")
                        ),
                        responseFields(
                                fieldWithPath("simpleScheduleResponseList").type(ARRAY).description("요청한 달의 나의 스케줄 리스트"),
                                fieldWithPath("simpleScheduleResponseList[0].scheduleId").type(NUMBER).description("스케줄 ID"),
                                fieldWithPath("simpleScheduleResponseList[0].localDate").type(STRING).description("스케줄 날짜"),
                                fieldWithPath("simpleScheduleResponseList[0].diseaseTagCount").type(NUMBER).description("증상 개수")
                        )
                ))
        ;
    }

    @Test
    void yearMonth_없이_월_스케줄_요청() throws Exception{
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
    void 토근_없이_월_스케줄_요청() throws Exception{
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
    void scheduleId로_스케줄_상세_조회() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        ScheduleResponseDto scheduleResponseDto = givenScheduleResponseDto();
        given(calendarQueryUseCase.getMySchedule(any(),any())).willReturn(scheduleResponseDto);


        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/{scheduleId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        pathParameters(
                                parameterWithName("scheduleId").description("조회할 스케줄 ID")
                        ),
                        responseFields(
                                fieldWithPath("scheduleId").type(NUMBER).description("스케줄 Id"),
                                fieldWithPath("localDate").type(STRING).description("스케줄 날짜"),
                                fieldWithPath("diseaseTagResponseList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("diseaseTagResponseList[0].name").type(STRING).description("질병 이름"),
                                fieldWithPath("dailyNote").type(STRING).description("하루 일기").optional(),
                                fieldWithPath("painType").type(STRING).description(generateLinkCode(PAIN_TYPE)).optional()
                        )
                ))
        ;
    }

    @Test
    void 토큰_없이_scheduleId로_스케줄_상세_조회() throws Exception{
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
    void 스케줄_id_없이_스케줄_상세_조회() throws Exception{
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
    void 자신의_스케줄_중_존재하지_않는_scheduleId로_스케줄_상세_조회() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new ScheduleNotFoundException()).given(calendarQueryUseCase).getMySchedule(any(),any());

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
    void 나의_스케줄_수정_성공() throws Exception{
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
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        pathParameters(
                                parameterWithName("scheduleId").description("수정할 스케줄 ID")
                        ),
                        requestFields(
                                fieldWithPath("diseaseTagRequestList").type(ARRAY).description("질병 태그 리스트"),
                                fieldWithPath("diseaseTagRequestList[0].diseaseType").type(STRING).description(generateLinkCode(DISEASE_TYPE)),
                                fieldWithPath("diseaseTagRequestList[0].name").type(STRING).description("질병 이름"),
                                fieldWithPath("dailyNote").type(STRING).description("아픔 일기").optional(),
                                fieldWithPath("painType").type(STRING).description("아픔 정도").optional()
                        )
                ))
        ;
    }

    @Test
    void 토큰_없이_스케줄_수정() throws Exception{
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
    void 스케줄_id_없이_스케줄_수정() throws Exception{
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
    void 나의_스케줄_수정_증상이_6개_들어온_경우_실패() throws Exception{
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
    void 나의_스케줄_수정_증상이_0개_들어온_경우_실패() throws Exception{
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
    void 자신의_스케줄_중_존재하지_않는_scheduleId로_스케줄_수정() throws Exception{
        // given
        ScheduleModifyRequest scheduleModifyRequest = givenScheduleModifyRequest();
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new ScheduleNotFoundException()).given(calendarCommandUseCase).modifyMySchedule(any(),any(),any());

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
                ));
        ;
    }


}
