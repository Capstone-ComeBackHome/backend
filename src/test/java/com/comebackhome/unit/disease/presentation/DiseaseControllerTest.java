package com.comebackhome.unit.disease.presentation;

import com.comebackhome.common.exception.disease.DiseaseNotFoundException;
import com.comebackhome.support.restdocs.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static com.comebackhome.support.DiseaseGivenHelper.givenDiseaseResponseDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/disease";

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
                                fieldWithPath("causeList").type(ARRAY).description("질병의 원인 리스트"),
                                fieldWithPath("hospitalCare").type(STRING).description("병원에서 제시하는 치료 방법"),
                                fieldWithPath("homeCareList").type(ARRAY).description("홈 케어 해결책 리스트"),
                                fieldWithPath("complications").type(STRING).description("합병증")
                        )
                ))
                ;
    }

   @Test
   void 없는_diseaseId로_상세조회() throws Exception{
       // given
       given(diseaseQueryUseCase.getDisease(any())).willThrow(new DiseaseNotFoundException());

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

}
