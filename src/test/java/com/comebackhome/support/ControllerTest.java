package com.comebackhome.support;

import com.comebackhome.authentication.application.AuthCommandUseCase;
import com.comebackhome.authentication.application.TokenProvider;
import com.comebackhome.authentication.domain.TokenRepository;
import com.comebackhome.authentication.presentation.AuthRestController;
import com.comebackhome.calendar.application.CalendarCommandUseCase;
import com.comebackhome.calendar.application.CalendarQueryUseCase;
import com.comebackhome.calendar.application.DiseaseTagQueryUseCase;
import com.comebackhome.calendar.presentation.CalendarRestController;
import com.comebackhome.calendar.presentation.DiseaseTagRestController;
import com.comebackhome.config.SecurityTestConfig;
import com.comebackhome.disease.application.DiseaseQueryUseCase;
import com.comebackhome.disease.presentation.DiseaseRestController;
import com.comebackhome.support.restdocs.common.CommonDocsController;
import com.comebackhome.support.restdocs.enums.EnumDocController;
import com.comebackhome.user.domain.Role;
import com.comebackhome.user.domain.User;
import com.comebackhome.user.domain.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Import(SecurityTestConfig.class)
@WebMvcTest({
        AuthRestController.class,
        CommonDocsController.class,
        DiseaseRestController.class,
        DiseaseTagRestController.class,
        CalendarRestController.class,
        EnumDocController.class
})
public abstract class ControllerTest {

    protected MockMvc mockMvc;

    @Autowired protected ObjectMapper objectMapper;
    @MockBean protected AuthCommandUseCase authCommandUseCase;
    @MockBean protected DiseaseQueryUseCase diseaseQueryUseCase;
    @MockBean protected DiseaseTagQueryUseCase diseaseTagQueryUseCase;
    @MockBean protected CalendarCommandUseCase calendarCommandUseCase;
    @MockBean protected CalendarQueryUseCase calendarQueryUseCase;

    // security
    @MockBean protected TokenProvider tokenProvider;
    @MockBean protected UserRepository userRepository;
    @MockBean protected TokenRepository tokenRepository;


    protected String createJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    protected void mockingSecurityFilterForLoginUserAnnotation(){
        given(tokenProvider.validateToken(any())).willReturn(true);
        given(tokenRepository.existsLogoutAccessTokenById(any())).willReturn(false);
        given(tokenRepository.existsLogoutRefreshTokenById(any())).willReturn(false);
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().id(1L).role(Role.USER).build()));
    }
}
