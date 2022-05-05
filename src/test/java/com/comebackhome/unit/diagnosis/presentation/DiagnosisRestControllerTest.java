package com.comebackhome.unit.diagnosis.presentation;

import com.comebackhome.common.exception.disease.DiagnosisNotFoundException;
import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.common.exception.disease.NotMyDiagnosisException;
import com.comebackhome.diagnosis.domain.service.dto.DiagnosisResponseDtoList;
import com.comebackhome.diagnosis.presentation.dto.request.DiagnosisSaveRequest;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static com.comebackhome.config.RestDocsConfig.field;
import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisRequest;
import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisResponseDtoList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiagnosisRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/diagnoses";
    private final String ACCESS_TOKEN = "Bearer accessToken";


    @Test
    @WithMockUser(roles = "USER")
    void 세가지_질병명으로_diagnosis_저장하기() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        DiagnosisSaveRequest diagnosisSaveRequest = givenDiagnosisRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestFields(
                                fieldWithPath("diseaseNameList").type(ARRAY).description("질병명 리스트").attributes(field("constraints", "최소 3개, 최대 3개"))
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 한가지_질병명으로_diagnosis_저장하기_실패() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        DiagnosisSaveRequest diagnosisSaveRequest = givenDiagnosisRequest();
        diagnosisSaveRequest.setDiseaseNameList(List.of("질병1"));

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptorIncludeErrorFields())
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 세가지_공백_질병명으로_diagnosis_저장하기_실패() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        DiagnosisSaveRequest diagnosisSaveRequest = givenDiagnosisRequest();
        diagnosisSaveRequest.setDiseaseNameList(List.of("","",""));

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptorIncludeErrorFields())
                ))
        ;
    }

    @Test
    void 토큰없이_diagnosis_저장하기_실패() throws Exception{

        // given
        DiagnosisSaveRequest diagnosisSaveRequest = givenDiagnosisRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 존재하지_않는_질병명으로_진단_저장하기_실패() throws Exception{
        //given
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new DiseaseNotFoundException()).given(diagnosisFacade).createDiagnosis(any(),any());
        DiagnosisSaveRequest diagnosisSaveRequest = givenDiagnosisRequest();

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))
        ;
    }


    @Test
    @WithMockUser(roles = "USER")
    void 나의_진단_내역_제거하기() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{diagnosisId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        pathParameters(
                                parameterWithName("diagnosisId").description("삭제할 진단 내역 Id")
                        )
                ))
        ;
    }

    @Test
    void 토큰_없이_나의_진단_내역_제거하기_실패() throws Exception{

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{diagnosisId}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ))
        ;
    }

    @Test
    void 진단_내역_id_없이_나의_진단_내역_제거하기_실패() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{diagnosisId}","")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))
        ;
    }

    @Test
    void 다른_사람의_진단_내역_제거하기_실패() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new NotMyDiagnosisException()).given(diagnosisFacade).deleteMyDiagnosis(any(),any());

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{diagnosisId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))

        ;
    }

    @Test
    void 존재하지_않는_진단_내역_제거하기_실패() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new DiagnosisNotFoundException()).given(diagnosisFacade).deleteMyDiagnosis(any(),any());

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.delete(URL+"/{diagnosisId}",1)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))

        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 나의_진단_내역_조회하기() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        DiagnosisResponseDtoList diagnosisResponseDtoList = givenDiagnosisResponseDtoList();
        given(diagnosisFacade.getMyDiagnoses(any(),any(),any())).willReturn(diagnosisResponseDtoList);


        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?lastDiagnosisId=20&size=20")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestParameters(
                                parameterWithName("lastDiagnosisId").description("조회했던 진단 Id중 가장 작은 Id").optional(),
                                parameterWithName("size").description("가져올 진단 내역 개수, 기본값은 20").optional()
                        ),
                        responseFields(
                                fieldWithPath("data.diagnosisResponseList").type(ARRAY).description("진단 내역 리스트"),
                                fieldWithPath("data.diagnosisResponseList[0].diagnosisId").type(NUMBER).description("진단 내역 Id").optional(),
                                fieldWithPath("data.diagnosisResponseList[0].createdDate").type(STRING).description("진단 시각").optional(),
                                fieldWithPath("data.diagnosisResponseList[0].diseaseNameList").type(ARRAY).description("진단된 질병 리스트").optional(),
                                fieldWithPath("data.hasNext").type(BOOLEAN).description("다음 페이지가 존재하는지 여부")
                        ).and(successDescriptors())
                ))
        ;
    }

    @Test
    void 토큰_없이_나의_진단_내역_조회하기() throws Exception{

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?lastDiagnosisId=20&size=20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ))
        ;
    }



}
