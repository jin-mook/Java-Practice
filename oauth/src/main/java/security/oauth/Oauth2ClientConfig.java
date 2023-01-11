package security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
@Configuration
public class Oauth2ClientConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authRequest -> authRequest.anyRequest().authenticated());
////        http.oauth2Login(oauth2 -> oauth2.loginPage("/loginPage"));
//        http.oauth2Login(Customizer.withDefaults());
//        http.logout()
//                .logoutSuccessHandler(oidcLogoutSuccessHandler())
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID");
//        return http.build();
//    }
    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/login").permitAll()
                .anyRequest().authenticated());
        http.oauth2Login(oauth2 -> oauth2.loginPage("/login")
                .authorizationEndpoint(authorizationEndpointConfig ->
                        authorizationEndpointConfig.baseUri("/oauth2/v1/authorization"))
                .redirectionEndpoint(redirectionEndpointConfig ->
                        redirectionEndpointConfig.baseUri("/login/v1/oauth2/code/*")));
        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {

        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("http://localhost:8081/login");

        return successHandler;
    }
}
