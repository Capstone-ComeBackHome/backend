package com.comebackhome.integration.calendar;

import com.comebackhome.authentication.application.TokenProvider;
import com.comebackhome.calendar.domain.DiseaseTag;
import com.comebackhome.calendar.infrastructure.repository.DiseaseTagJpaRepository;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.comebackhome.calendar.domain.DiseaseType.*;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static com.comebackhome.support.helper.UserGivenHelper.createAuthentication;
import static com.comebackhome.support.helper.UserGivenHelper.givenUser;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseTagIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/diseaseTags";
    private final String TOKEN_TYPE = "Bearer ";


    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;
    @Autowired DiseaseTagJpaRepository diseaseTagJpaRepository;

    @Test
    void CustomType을_제외한_모든_diseaseTag_가져오기() throws Exception{
        // given
        createDiseaseTagListAndSave();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .header(HttpHeaders.AUTHORIZATION,TOKEN_TYPE + createAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.headDiseaseTagList[0].diseaseType", is(HEAD.name())))
                .andExpect(jsonPath("$.headDiseaseTagList[0].name", is("두통")))
                .andExpect(jsonPath("$.bronchusDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.bronchusDiseaseTagList[0].diseaseType", is(BRONCHUS.name())))
                .andExpect(jsonPath("$.bronchusDiseaseTagList[0].name", is("코막힘")))
                .andExpect(jsonPath("$.chestDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.chestDiseaseTagList[0].diseaseType", is(CHEST.name())))
                .andExpect(jsonPath("$.chestDiseaseTagList[0].name", is("가슴 통증")))
                .andExpect(jsonPath("$.stomachDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.stomachDiseaseTagList[0].diseaseType", is(STOMACH.name())))
                .andExpect(jsonPath("$.stomachDiseaseTagList[0].name", is("공복감")))
                .andExpect(jsonPath("$.limbDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.limbDiseaseTagList[0].diseaseType", is(LIMB.name())))
                .andExpect(jsonPath("$.limbDiseaseTagList[0].name", is("관절통")))
                .andExpect(jsonPath("$.skinDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.skinDiseaseTagList[0].diseaseType", is(SKIN.name())))
                .andExpect(jsonPath("$.skinDiseaseTagList[0].name", is("여드름")))
        ;
    }

    private void createDiseaseTagListAndSave() {
        List<DiseaseTag> diseaseTagList = List.of(givenDiseaseTag(HEAD, "두통"),
                givenDiseaseTag(BRONCHUS, "코막힘"),
                givenDiseaseTag(CHEST, "가슴 통증"),
                givenDiseaseTag(STOMACH, "공복감"),
                givenDiseaseTag(LIMB, "관절통"),
                givenDiseaseTag(SKIN, "여드름"));

        diseaseTagJpaRepository.saveAll(diseaseTagList);
    }

}
