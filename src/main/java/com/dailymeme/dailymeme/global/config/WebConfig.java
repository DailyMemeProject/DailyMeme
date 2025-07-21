package com.dailymeme.dailymeme.global.config;


import com.dailymeme.dailymeme.global.token.filter.JwtAuthFilter;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig {

    private static final String[] WHITE_LIST = {"/api/users/signup", "/api/users/login", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"};

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
//    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
//    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
//    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors-> cors.configurationSource(request -> {
            var corsConfig = new CorsConfiguration();
            corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","SEND", "OPTIONS")); // 사용 메서드 허용
            corsConfig.setAllowedHeaders(List.of("*")); //추후 헤더에 auth 값을 받기 위한 설정
            corsConfig.setAllowCredentials(true); // 쿠키 전달 허용
            corsConfig.setAllowedOrigins(List.of("http://localhost:8080"));
            return corsConfig;
        }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR).permitAll()
//                                .requestMatchers(HttpMethod.POST, "/api/users/set").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT 기반 인증에서 Stateless 유지
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/oauth2/authorization/google")
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserService))
//                        .successHandler(oAuth2LoginSuccessHandler)
//                        .failureHandler(oAuth2LoginFailureHandler)
//                );
        return http.build();
    }


}
