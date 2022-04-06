package com.comebackhome.unit.user.presentation;

import com.comebackhome.support.restdocs.RestDocsTestSupport;
import com.comebackhome.user.presentation.dto.UserInfoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static com.comebackhome.config.RestDocsConfig.field;
import static com.comebackhome.support.helper.UserGivenHelper.givenUserInfoRequest;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerTest extends RestDocsTestSupport {

    private final String URL = "/api/v1/users";
    private final String ACCESS_TOKEN = "Bearer accessToken";


    @Test
    @WithMockUser(roles = "USER")
    void 개인_정보_업데이트_하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserInfoRequest userInfoRequest = givenUserInfoRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userInfoRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age").type(NUMBER).description("나이").attributes(field("constraints", "양수")),
                                fieldWithPath("sex").type(STRING).description("성별").attributes(field("constraints", "MAN, WOMAN 중 하나")),
                                fieldWithPath("height").type(NUMBER).description("키").attributes(field("constraints", "양수")),
                                fieldWithPath("weight").type(NUMBER).description("몸무게").attributes(field("constraints", "양수")),
                                fieldWithPath("history").type(STRING).description("과거력").optional(),
                                fieldWithPath("familyHistory").type(STRING).description("가족력").optional(),
                                fieldWithPath("drugHistory").type(STRING).description("약물투약력").optional(),
                                fieldWithPath("socialHistory").type(STRING).description("사회력").optional(),
                                fieldWithPath("traumaHistory").type(STRING).description("외상력").optional()
                        )
                ))
        ;
    }

    @Test
    void 토큰_없이_개인_정보_업데이트() throws Exception{
        // given
        UserInfoRequest userInfoRequest = givenUserInfoRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .content(createJson(userInfoRequest))
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
    @WithMockUser(roles = "USER")
    void 필수값이_들어오지_않은_상태에서_개인_정보_업데이트_하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserInfoRequest userInfoRequest = UserInfoRequest.builder().build();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userInfoRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                      responseFields(
                              errorDescriptorIncludeErrorFields()
                      )
                ))
        ;
    }
}
