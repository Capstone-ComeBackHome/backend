package com.comebackhome.support;

import com.comebackhome.authentication.application.AuthCommandUseCase;
import com.comebackhome.authentication.presentation.AuthRestController;
import com.comebackhome.config.SecurityTestConfig;
import com.comebackhome.disease.application.DiseaseQueryUseCase;
import com.comebackhome.disease.presentation.DiseaseController;
import com.comebackhome.support.restdocs.DocsController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import(SecurityTestConfig.class)
@WebMvcTest({
        AuthRestController.class,
        DocsController.class,
        DiseaseController.class
})
public abstract class ControllerTest {

    protected MockMvc mockMvc;

    @Autowired protected ObjectMapper objectMapper;

    @MockBean protected AuthCommandUseCase authCommandUseCase;

    @MockBean protected DiseaseQueryUseCase diseaseQueryUseCase;

    protected String createJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }
}
