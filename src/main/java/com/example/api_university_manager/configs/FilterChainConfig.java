package com.example.api_university_manager.configs;

import com.example.api_university_manager.components.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class FilterChainConfig {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(request -> request
                        //.requestMatchers(new RegexRequestMatcher("/^(course|student|professor)/admin/.*$", null)).hasRole("ADMIN")
                        //.requestMatchers(new RegexRequestMatcher("/^(course|student|professor)/developer/.*$", null)).hasRole("DEVELOPER")
                        .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
