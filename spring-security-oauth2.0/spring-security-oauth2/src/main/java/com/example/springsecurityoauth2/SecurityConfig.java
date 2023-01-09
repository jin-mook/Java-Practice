package com.example.springsecurityoauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin();   // form 인증 설정
        http.httpBasic();   // httpBasic 인증 설정
        http.csrf();
//        http.apply(new CustomSecurityConfigure().setFlag(false));
//        http.httpBasic().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        // custom 설정 -> 가장 우선순위를 가짐
//        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
//            @Override
//            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                System.out.println("custom entryPoint");
//            }
//        });
        return http.build();
    }

    @Bean
    SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/**").authenticated();

        http.formLogin();   // form 인증 설정
        http.csrf();
        return http.build();
    }
}
