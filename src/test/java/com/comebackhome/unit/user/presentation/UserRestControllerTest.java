package com.comebackhome.unit.user.presentation;

import com.comebackhome.support.restdocs.RestDocsTestSupport;
import com.comebackhome.user.domain.service.dto.UserEssentialUpdateRequestDto;
import com.comebackhome.user.domain.service.dto.UserMedicineUpdateRequestDto;
import com.comebackhome.user.presentation.dto.request.UserInfoSaveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static com.comebackhome.config.RestDocsConfig.field;
import static com.comebackhome.support.helper.UserGivenHelper.*;
import static com.comebackhome.support.restdocs.enums.DocumentLinkGenerator.DocUrl.AUTH_PROVIDER;
import static com.comebackhome.support.restdocs.enums.DocumentLinkGenerator.DocUrl.SEX;
import static com.comebackhome.support.restdocs.enums.DocumentLinkGenerator.generateLinkCode;
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
        UserInfoSaveRequest userInfoSaveRequest = givenUserInfoRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userInfoSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age").type(NUMBER).description("나이").attributes(field("constraints", "양수")),
                                fieldWithPath("sex").type(STRING).description(generateLinkCode(SEX)),
                                fieldWithPath("height").type(NUMBER).description("키").attributes(field("constraints", "양수")),
                                fieldWithPath("weight").type(NUMBER).description("몸무게").attributes(field("constraints", "양수")),
                                fieldWithPath("history").type(STRING).description("과거력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("familyHistory").type(STRING).description("가족력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("drugHistory").type(STRING).description("약물투약력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("socialHistory").type(STRING).description("사회력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("traumaHistory").type(STRING).description("외상력").optional().attributes(field("constraints", "50자 이내"))
                        )
                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 부가정보_50자_초과_개인_정보_업데이트_하기_실패() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserInfoSaveRequest userInfoSaveRequest = givenUserInfoRequest();
        setExceedSizeFields(userInfoSaveRequest);


        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userInfoSaveRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ))
        ;
    }

    private void setExceedSizeFields(UserInfoSaveRequest userInfoSaveRequest) {
        userInfoSaveRequest.setHistory("50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도");
        userInfoSaveRequest.setDrugHistory("50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도");
        userInfoSaveRequest.setFamilyHistory("50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도");
        userInfoSaveRequest.setSocialHistory("50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도");
        userInfoSaveRequest.setTraumaHistory("50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도50자초과시도");
    }

    @Test
    void 토큰_없이_개인_정보_업데이트() throws Exception{
        // given
        UserInfoSaveRequest userInfoSaveRequest = givenUserInfoRequest();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .content(createJson(userInfoSaveRequest))
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
        UserInfoSaveRequest userInfoSaveRequest = UserInfoSaveRequest.builder().build();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL)
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userInfoSaveRequest))
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
    void 개인_정보_심플_조회하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();

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
                                fieldWithPath("email").type(STRING).description("이메일"),
                                fieldWithPath("name").type(STRING).description("실명"),
                                fieldWithPath("picture").type(STRING).description("사진 url").optional(),
                                fieldWithPath("authProvider").type(STRING).description(generateLinkCode(AUTH_PROVIDER))
                        )

                ))
        ;
    }

    @Test
    void 토큰_없이_개인_정보_심플_조회하기() throws Exception{
        // given

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL)
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
    void 개인_정보_히스토리_조회하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/info")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        responseFields(
                                fieldWithPath("age").type(NUMBER).description("나이"),
                                fieldWithPath("sex").type(STRING).description(generateLinkCode(SEX)),
                                fieldWithPath("height").type(NUMBER).description("키"),
                                fieldWithPath("weight").type(NUMBER).description("몸무게"),
                                fieldWithPath("history").type(STRING).description("과거력").optional(),
                                fieldWithPath("drugHistory").type(STRING).description("약물투여력").optional(),
                                fieldWithPath("socialHistory").type(STRING).description("사회력").optional(),
                                fieldWithPath("traumaHistory").type(STRING).description("외상력").optional(),
                                fieldWithPath("familyHistory").type(STRING).description("가족력").optional()
                        )

                ))
        ;
    }


    @Test
    void 토큰_없이_개인_정보_히스토리_조회하기() throws Exception{
        // given

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/info")
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
    void 필수_정보_조회하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        responseFields(
                                fieldWithPath("age").type(NUMBER).description("나이"),
                                fieldWithPath("sex").type(STRING).description(generateLinkCode(SEX)),
                                fieldWithPath("weight").type(NUMBER).description("몸무게"),
                                fieldWithPath("height").type(NUMBER).description("키")
                        )

                ))
        ;
    }

    @Test
    void 토큰_없이_필수_정보_조회하기() throws Exception{
        // given

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/essential")
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
    void 필수_정보_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userEssentialUpdateRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestFields(
                                fieldWithPath("age").type(NUMBER).description("나이").attributes(field("constraints", "양수")),
                                fieldWithPath("sex").type(STRING).description(generateLinkCode(SEX)),
                                fieldWithPath("height").type(NUMBER).description("키").attributes(field("constraints", "양수")),
                                fieldWithPath("weight").type(NUMBER).description("몸무게").attributes(field("constraints", "양수"))
                        )

                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 필수_정보_나이_없이_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();
        userEssentialUpdateRequestDto.setAge(0);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userEssentialUpdateRequestDto))
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
    void 필수_정보_성별_없이_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();
        userEssentialUpdateRequestDto.setSex(null);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userEssentialUpdateRequestDto))
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
    void 필수_정보_몸무게_없이_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();
        userEssentialUpdateRequestDto.setWeight(0);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userEssentialUpdateRequestDto))
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
    void 필수_정보_키_없이_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();
        userEssentialUpdateRequestDto.setHeight(0);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userEssentialUpdateRequestDto))
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
    void 필수_정보_전부_없이_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();
        userEssentialUpdateRequestDto.setHeight(0);
        userEssentialUpdateRequestDto.setSex(null);
        userEssentialUpdateRequestDto.setWeight(0);
        userEssentialUpdateRequestDto.setHeight(0);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userEssentialUpdateRequestDto))
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
    void 토큰_없이_필수_정보_수정하기() throws Exception{
        // given
        UserEssentialUpdateRequestDto userEssentialUpdateRequestDto = givenUserEssentialUpdateRequestDto();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/essential")
                .content(createJson(userEssentialUpdateRequestDto))
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
    void 부가_정보_조회하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/medicine")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        responseFields(
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
    void 토큰_없이_부가_정보_조회하기() throws Exception{
        // given

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.get(URL+"/medicine")
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
    void 부가_정보_수정하기() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserMedicineUpdateRequestDto userMedicineUpdateRequestDto = givenUserMedicineUpdateRequestDto();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/medicine")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userMedicineUpdateRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocumentationResultHandler.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Bearer 타입 Access Token")
                        ),
                        requestFields(
                                fieldWithPath("history").type(STRING).description("과거력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("familyHistory").type(STRING).description("가족력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("drugHistory").type(STRING).description("약물투약력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("socialHistory").type(STRING).description("사회력").optional().attributes(field("constraints", "50자 이내")),
                                fieldWithPath("traumaHistory").type(STRING).description("외상력").optional().attributes(field("constraints", "50자 이내"))
                        )

                ))
        ;
    }

    @Test
    @WithMockUser(roles = "USER")
    void 부가_정보_50자_초과_수정하기_실패() throws Exception{
        // given
        mockingSecurityFilterForLoginUserAnnotation();
        UserMedicineUpdateRequestDto userMedicineUpdateRequestDto = givenUserMedicineUpdateRequestDto();
        setExceedSizeFields(userMedicineUpdateRequestDto);

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/medicine")
                .header(HttpHeaders.AUTHORIZATION,ACCESS_TOKEN)
                .content(createJson(userMedicineUpdateRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(restDocumentationResultHandler.document(
                        responseFields(
                                errorDescriptorIncludeErrorFields()
                        )
                ))
        ;
    }

    private void setExceedSizeFields(UserMedicineUpdateRequestDto userMedicineUpdateRequestDto) {
        userMedicineUpdateRequestDto.setHistory("50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력");
        userMedicineUpdateRequestDto.setDrugHistory("50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력");
        userMedicineUpdateRequestDto.setFamilyHistory("50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력");
        userMedicineUpdateRequestDto.setTraumaHistory("50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력");
        userMedicineUpdateRequestDto.setSocialHistory("50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력50자초과입력");
    }

    @Test
    void 토큰_없이_부가_정보_수정하기() throws Exception{
        // given
        UserMedicineUpdateRequestDto userMedicineUpdateRequestDto = givenUserMedicineUpdateRequestDto();

        // when then docs
        mockMvc.perform(RestDocumentationRequestBuilders.patch(URL+"/medicine")
                .content(createJson(userMedicineUpdateRequestDto))
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
