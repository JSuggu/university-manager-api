package com.example.api_university_manager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(request -> request
                        //.requestMatchers(new RegexRequestMatcher("/^(course|student|professor)/admin/.*$", null)).hasRole("DEVELOPER")
                        //.requestMatchers(new RegexRequestMatcher("/^(course|student|professor)/developer/.*$", null)).hasRole("ADMIN")
                        .anyRequest().permitAll())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .defaultSuccessUrl("/degrees/get-all")
                        .permitAll());
        return http.build();
    }

}
