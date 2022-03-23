package com.comebackhome.unit.disease.presentation;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.disease.application.dto.SimpleDiseaseResponseDto;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.util.List;

import static com.comebackhome.support.helper.DiseaseGivenHelper.givenDiseaseResponseDto;
import static com.comebackhome.support.helper.DiseaseGivenHelper.givenSimpleDiseaseResponseDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/diseases";

    @Test
    void diseaseId로_상세정보_찾기() throws Exception{
        // given
        given(diseaseQueryUseCase.getDisease(any())).willReturn(givenDiseaseResponseDto());

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?diseaseId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestParameters(
                                parameterWithName("diseaseId").description("질병 ID값")
                        ),
                        responseFields(
                                fieldWithPath("name").type(STRING).description("질병 이름"),
                                fieldWithPath("definition").type(STRING).description("질병 정의"),
                                fieldWithPath("recommendDepartment").type(STRING).description("추천 진료과"),
                                fieldWithPath("symptom").type(STRING).description("질병의 증상"),
                                fieldWithPath("cause").type(STRING).description("질병의 원인"),
                                fieldWithPath("hospitalCare").type(STRING).description("병원에서 제시하는 치료 방법")
                        )
                ))
                ;
    }

   @Test
   void 없는_diseaseId로_상세조회() throws Exception{
       // given
       willThrow(new DiseaseNotFoundException()).given(diseaseQueryUseCase).getDisease(any());

       // when then docs
       mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"?diseaseId=1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andDo(restDocumentationResultHandler.document(
                       responseFields(
                               errorDescriptors()
                       )
               ));
   }


    @Test
    void diseaseId_파라미터_없이_상세조회() throws Exception{
        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptors()
                        )
                ));
    }

    @Test
    void 여러_질병명으로_간략하게_질병_조회하기() throws Exception{
        // given
        List<SimpleDiseaseResponseDto> simpleDiseaseResponseDtoList = List.of(
                        givenSimpleDiseaseResponseDto("부정맥",1L),
                        givenSimpleDiseaseResponseDto("후두염",2L),
                        givenSimpleDiseaseResponseDto("편도염",3L));

        given(diseaseQueryUseCase.getSimpleDiseaseList(any())).willReturn(simpleDiseaseResponseDtoList);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/simple?diseaseNameList=부정맥,후두염,편도염")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestParameters(
                                parameterWithName("diseaseNameList").description("질병 이름 리스트")
                        ),
                        responseFields(
                                fieldWithPath("simpleDiseaseList[0].diseaseId").type(NUMBER).description("질병 ID"),
                                fieldWithPath("simpleDiseaseList[0].name").type(STRING).description("질병 이름"),
                                fieldWithPath("simpleDiseaseList[0].definition").type(STRING).description("질병 정의"),
                                fieldWithPath("simpleDiseaseList[0].recommendDepartment").type(STRING).description("추천 진료과")
                        )
                ))
                ;
    }

    @Test
    void 질병명이_콤마를_기준으로_빈칸으로_들어온_경우_실패() throws Exception{
        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/simple?diseaseNameList=,,")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))
        ;
    }

    @Test
    void 질병명이_빈칸으로_들어온_경우_실패() throws Exception{
        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/simple?diseaseNameList=")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))
        ;
    }

    @Test
    void 질병명_파라미터가_들어오지_않은_경우_실패() throws Exception{
        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/simple")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(errorDescriptors())
                ))
        ;
    }

}
