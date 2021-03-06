package com.comebackhome.support.restdocs.common;


import com.comebackhome.common.exception.GlobalExceptionHandler;
import com.comebackhome.config.RestDocsConfig;
import com.comebackhome.support.restdocs.SampleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(RestDocsConfig.class)
@ExtendWith(RestDocumentationExtension.class)
public class CommonDocsControllerTest {


    MockMvc mockMvc;
    RestDocumentationResultHandler restDocumentationResultHandler;

    @BeforeEach
    void setUp(final RestDocumentationContextProvider provider) {
        this.restDocumentationResultHandler = MockMvcRestDocumentation.document(
                "{class-name}/{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

        this.mockMvc = MockMvcBuilders.standaloneSetup(CommonDocsController.class)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .setControllerAdvice(GlobalExceptionHandler.class)
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocumentationResultHandler)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }


    /**
     * ?????? ???????????? ?????? ??????????????? ???????????? ????????? ?????? api ?????? ??? ????????? ?????????????????? rest docs ?????? ?????????
     * standalone?????? ????????? ?????? ????????? rest docs ?????? ??????????????? ????????? ????????? ?????? ??????
     */

    @Test
    void ??????_?????????() throws Exception{
        // when then
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/oauth2/authorization/{provider}","kakao")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        pathParameters(
                                parameterWithName("provider").description("?????? ????????? ?????????, [kakao, naver, google]")
                        ),
                        responseFields(
                                fieldWithPath("data.tokenType").type(STRING).description("?????? ??????"),
                                fieldWithPath("data.accessToken").type(STRING).description("?????? ????????? ??? ???????????? access ??????"),
                                fieldWithPath("data.refreshToken").type(STRING).description("access ?????? ????????? ???????????? ???????????? refresh ??????"),
                                fieldWithPath("result").type(STRING).description("?????? ??? SUCCESS, ????????? FAIL"),
                                fieldWithPath("message").description("?????? ?????????"),
                                fieldWithPath("code").description("?????? ??????"),
                                fieldWithPath("errors").description("Error ??? ?????? ???")
                        )
                ))

        ;
    }

    @Test
    void ??????_??????() throws Exception{
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        SampleRequest sampleRequest = new SampleRequest("backtony");
        String content = objectMapper.writeValueAsString(sampleRequest);
        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get("/docs/error")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        PayloadDocumentation.responseFields(
                                fieldWithPath("result").type(STRING).description("?????? ??? SUCCESS, ????????? FAIL"),
                                fieldWithPath("data").description("?????? ?????????"),
                                fieldWithPath("message").description("?????? ?????????"),
                                fieldWithPath("code").description("?????? ??????"),
                                fieldWithPath("errors").description("Error ??? ?????? ???"),
                                fieldWithPath("errors[0].field").description("?????? ?????????").optional(),
                                fieldWithPath("errors[0].value").description("?????? ?????????").optional(),
                                fieldWithPath("errors[0].reason").description("?????? ??????").optional())
                ));
    }
}
