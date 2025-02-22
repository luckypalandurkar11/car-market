package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http.csrf().disable().cors().disable();
        http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

//        http.authorizeHttpRequests()
//                .requestMatchers("/api/b2/auth/signup", "/api/b2/auth/signin", "/api/b2/auth/content-manager-signup", "/api/b2/auth/login-otp", "/api/b2/auth/blog-manager-signup")
//                .permitAll()
//                .requestMatchers("/api/c2/cars").hasRole("CONTENTMANAGER")
//                .anyRequest()
//                .authenticated();


        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
