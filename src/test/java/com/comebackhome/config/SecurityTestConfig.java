package com.comebackhome.config;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.comebackhome.config.security")
public class SecurityTestConfig {
}
