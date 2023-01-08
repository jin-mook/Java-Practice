package com.example.springsecurityoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class SpringSecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2Application.class, args);
    }

}
