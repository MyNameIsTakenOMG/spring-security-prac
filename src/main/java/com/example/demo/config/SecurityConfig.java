package com.example.demo.config;

import com.example.demo.error_handlers.CustomAccessDenied;
import com.example.demo.error_handlers.CustomAuthenticationEntryPoint;
//import com.example.demo.error_handlers.DelegateAccessDenied;
//import com.example.demo.error_handlers.DelegateAuthenticationEntryPoint;
import com.example.demo.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {
//    @Autowired
//    @Qualifier("DelegateAccessDenied")
//    private DelegateAccessDenied delegateAccessDenied;
//    @Autowired
//    @Qualifier("DelegateAuthenticationEntryPoint")
//    private DelegateAuthenticationEntryPoint delegateAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDenied customAccessDenied;
    @Autowired
    // AuthenticationEntryPoint can be overwritten for different scenarios
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        // by default "_csrf" csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName("_csrf");

//        http.securityContext(httpSecuritySecurityContextConfigurer -> {
//            httpSecuritySecurityContextConfigurer.requireExplicitSave(false);
//        });
//        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
//            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//        });

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConvertor());

        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(List.of("*"));
                corsConfiguration.setAllowedMethods(List.of("*"));
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setMaxAge(3600L);
                corsConfiguration.setAllowedHeaders(List.of("*"));
                corsConfiguration.setExposedHeaders(List.of("Authorization"));
                return corsConfiguration;
            });
        });
        http.csrf(csrf-> {
            csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                .ignoringRequestMatchers("/contact","/register")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                })
                .authorizeHttpRequests(requests->requests
//                        .requestMatchers("/myAccount").hasAnyAuthority("VIEWACCOUNT")
//                        .requestMatchers("/myCards").hasAnyAuthority("VIEWCARDS")
//                        .requestMatchers("/myLoan").hasAnyAuthority("VIEWLOAN")
//                        .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
                        .requestMatchers("/myAccount").hasRole("USER")
                        .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/myLoans").authenticated()
                        .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices","/contact","/register").permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
                    httpSecurityOAuth2ResourceServerConfigurer.jwt(jwtConfigurer -> {
                        jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter);
                    });
                });

        http.addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class);
//        http.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class);
//        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
//        http.addFilterAfter(new JWTTokenGenerateFilter(),BasicAuthenticationFilter.class);
//        http.addFilterBefore(new JWTTokenValidateFilter(), BasicAuthenticationFilter.class);
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
//            httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(delegateAccessDenied);
//            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(delegateAuthenticationEntryPoint);
            httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(customAccessDenied); // handle 403 unauthorized (need a customized accessDeniedHandler)
            httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(customAuthenticationEntryPoint); // handle 401 unauthenticated (need a customized authenticationEntryPoint)
        });
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
//    @Bean
//    public JdbcUserDetailsManager userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }


// as a resource server, we reply on KeyCloak to authenticate users
//    @Bean
//    public PasswordEncoder passwordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
//    }
}
