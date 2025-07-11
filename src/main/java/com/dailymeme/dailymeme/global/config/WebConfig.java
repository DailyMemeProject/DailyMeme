package com.dailymeme.dailymeme.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {

    private static final String[] WHITE_LIST = {"/api/users/signup", "/api/users/login"};


}
