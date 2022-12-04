//package com.example.springsecurityoauth2;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//
//public class CustomSecurityConfigure extends AbstractHttpConfigurer<CustomSecurityConfigure, HttpSecurity> {
//
//    private boolean isSecure;
//
//    @Override
//    public void init(HttpSecurity builder) throws Exception {
//        super.init(builder);
//        System.out.println("init method started...");
//    }
//
//    @Override
//    public void configure(HttpSecurity builder) throws Exception {
//        super.configure(builder);
//        System.out.println("configure method started...");
//        if (isSecure) {
//            System.out.println("https is required");
//        } else {
//            System.out.println("https is optional");
//        }
//    }
//
//    public CustomSecurityConfigure setFlag(boolean isSecure) {
//        this.isSecure = isSecure;
//        return this;
//    }
//}
