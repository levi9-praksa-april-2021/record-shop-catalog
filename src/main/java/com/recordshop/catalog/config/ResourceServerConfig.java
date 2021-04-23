package com.recordshop.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/records/**")
                .authorizeRequests()
                .antMatchers("/records/**").access("hasAuthority('SCOPE_catalog.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
