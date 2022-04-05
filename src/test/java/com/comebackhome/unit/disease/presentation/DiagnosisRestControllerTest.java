package com.comebackhome.unit.disease.presentation;

import com.comebackhome.common.exception.disease.DiagnosisNotFoundException;
import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.common.exception.disease.NotMyDiagnosisException;
import com.comebackhome.disease.presentation.dto.DiagnosisRequest;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static com.comebackhome.config.RestDocsConfig.field;
import static com.comebackhome.support.helper.DiagnosisGivenHelper.givenDiagnosisRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiagnosisRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/diagnoses";
    private final String ACCESS_TOKEN = "Bearer accessToken";


    @Test
    @WithMockUser(roles = "USER")
    void 세가지_질병명으로_diagnosis_저장하기() throws Exception{

        // given
        mockingSecurityFilterForLoginUserAnnotation();
        DiagnosisRequest diagnosisRequest = givenDiagnosisRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisRequest))
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
        DiagnosisRequest diagnosisRequest = givenDiagnosisRequest();
        diagnosisRequest.setDiseaseNameList(List.of("질병1"));

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisRequest))
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
        DiagnosisRequest diagnosisRequest = givenDiagnosisRequest();
        diagnosisRequest.setDiseaseNameList(List.of("","",""));

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisRequest))
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
        DiagnosisRequest diagnosisRequest = givenDiagnosisRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 존재하지_않는_질병명으로_진단_저장하기_실패() throws Exception{
        //given
        mockingSecurityFilterForLoginUserAnnotation();
        willThrow(new DiseaseNotFoundException()).given(diagnosisCommandUseCase).createDiagnosis(any(),any());
        DiagnosisRequest diagnosisRequest = givenDiagnosisRequest();

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(diagnosisRequest))
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
        willThrow(new NotMyDiagnosisException()).given(diagnosisCommandUseCase).deleteMyDiagnosis(any(),any());

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
        willThrow(new DiagnosisNotFoundException()).given(diagnosisCommandUseCase).deleteMyDiagnosis(any(),any());

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





}
