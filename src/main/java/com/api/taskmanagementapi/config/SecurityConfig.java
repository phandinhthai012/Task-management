package com.api.taskmanagementapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Tắt CSRF (Cross-Site Request Forgery) để các request POST/PUT/DELETE có thể chạy qua
                .csrf(AbstractHttpConfigurer::disable)

                // Cấu hình phân quyền request
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép TẤT CẢ các request đều được đi qua mà không cần check
                );

        return http.build();
    }
}
