package com.comebackhome.integration.calendar;

import com.comebackhome.calendar.domain.diseasetag.DiseaseTag;
import com.comebackhome.calendar.infrastructure.repository.diseasetag.DiseaseTagJpaRepository;
import com.comebackhome.support.IntegrationTest;
import com.comebackhome.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.comebackhome.calendar.domain.diseasetag.DiseaseType.*;
import static com.comebackhome.support.helper.CalendarGivenHelper.givenDiseaseTag;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DiseaseTagIntegrationTest extends IntegrationTest {

    private final String URL = "/api/v1/diseaseTags";
    private final String TOKEN_TYPE = "Bearer ";


    @Autowired UserRepository userRepository;
    @Autowired DiseaseTagJpaRepository diseaseTagJpaRepository;

    @Test
    void CustomType을_제외한_모든_diseaseTag_가져오기() throws Exception{
        // given
        createDiseaseTagListAndSave();

        // when then
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .header(HttpHeaders.AUTHORIZATION, TOKEN_TYPE + createAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.headDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.data.headDiseaseTagList[0].diseaseType", is(HEAD.name())))
                .andExpect(jsonPath("$.data.headDiseaseTagList[0].name", is("두통")))
                .andExpect(jsonPath("$.data.bronchusDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.data.bronchusDiseaseTagList[0].diseaseType", is(BRONCHUS.name())))
                .andExpect(jsonPath("$.data.bronchusDiseaseTagList[0].name", is("코막힘")))
                .andExpect(jsonPath("$.data.chestDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.data.chestDiseaseTagList[0].diseaseType", is(CHEST.name())))
                .andExpect(jsonPath("$.data.chestDiseaseTagList[0].name", is("가슴 통증")))
                .andExpect(jsonPath("$.data.stomachDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.data.stomachDiseaseTagList[0].diseaseType", is(STOMACH.name())))
                .andExpect(jsonPath("$.data.stomachDiseaseTagList[0].name", is("공복감")))
                .andExpect(jsonPath("$.data.limbDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.data.limbDiseaseTagList[0].diseaseType", is(LIMB.name())))
                .andExpect(jsonPath("$.data.limbDiseaseTagList[0].name", is("관절통")))
                .andExpect(jsonPath("$.data.skinDiseaseTagList.length()", is(1)))
                .andExpect(jsonPath("$.data.skinDiseaseTagList[0].diseaseType", is(SKIN.name())))
                .andExpect(jsonPath("$.data.skinDiseaseTagList[0].name", is("여드름")))
                .andExpectAll(
                        expectCommonSuccess()
                )
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
