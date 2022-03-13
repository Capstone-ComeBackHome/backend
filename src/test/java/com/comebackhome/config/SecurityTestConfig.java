package com.comebackhome.config;


import com.comebackhome.authentication.application.TokenProvider;
import com.comebackhome.authentication.domain.TokenRepository;
import com.comebackhome.user.domain.UserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.comebackhome.config.security")
public class SecurityTestConfig {

    @MockBean protected TokenProvider tokenProvider;
    @MockBean protected UserRepository userRepository;
    @MockBean protected TokenRepository tokenRepository;

}
