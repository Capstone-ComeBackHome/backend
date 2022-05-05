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
                .andExpect(jsonPath("$.data.head.diseaseTypeDescription", is(HEAD.getDescription())))
                .andExpect(jsonPath("$.data.head.diseaseTagNameList[0]", is("두통")))
                .andExpect(jsonPath("$.data.bronchus.diseaseTypeDescription", is(BRONCHUS.getDescription())))
                .andExpect(jsonPath("$.data.bronchus.diseaseTagNameList[0]", is("코막힘")))
                .andExpect(jsonPath("$.data.chest.diseaseTypeDescription", is(CHEST.getDescription())))
                .andExpect(jsonPath("$.data.chest.diseaseTagNameList[0]", is("가슴 통증")))
                .andExpect(jsonPath("$.data.stomach.diseaseTypeDescription", is(STOMACH.getDescription())))
                .andExpect(jsonPath("$.data.stomach.diseaseTagNameList[0]", is("공복감")))
                .andExpect(jsonPath("$.data.limb.diseaseTypeDescription", is(LIMB.getDescription())))
                .andExpect(jsonPath("$.data.limb.diseaseTagNameList[0]", is("관절통")))
                .andExpect(jsonPath("$.data.skin.diseaseTypeDescription", is(SKIN.getDescription())))
                .andExpect(jsonPath("$.data.skin.diseaseTagNameList[0]", is("여드름")))
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
