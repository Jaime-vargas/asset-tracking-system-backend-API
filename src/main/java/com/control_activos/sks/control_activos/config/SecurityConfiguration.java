package com.control_activos.sks.control_activos.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HTTP FILTER CHAIN CONFIGURATION
        http
            .csrf(AbstractHttpConfigurer::disable)
                // JWT = STATELESS
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                            ))
                // ACCESS RULES
            .authorizeHttpRequests(auth -> auth
                    //temp for view images
                    .requestMatchers("/uploads/**").permitAll()
                    .requestMatchers("/api/v1/**").permitAll()
                    .requestMatchers("/test/public").permitAll()
                    .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                    .anyRequest().permitAll()
            ).addFilterBefore(
                authenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        corsConfiguration(http);
        return http.build();
    }

    public void corsConfiguration(HttpSecurity http) throws Exception {
        http.cors(cors -> cors
            .configurationSource(request -> {
                var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                corsConfig.setAllowedOrigins(java.util.List.of("*"));
                corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(java.util.List.of("*"));
                return corsConfig;
            })
        );
    }
}
