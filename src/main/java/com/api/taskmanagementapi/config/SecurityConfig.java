package com.api.taskmanagementapi.config;

import com.api.taskmanagementapi.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

        http
                // Tắt CSRF (Cross-Site Request Forgery) để các request POST/PUT/DELETE có thể chạy qua
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Cấu hình phân quyền request
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/users/**",
                                "/api/healthCheck/**"
                        ).permitAll() // Cho phép đăng ký/đăng nhập
                        .anyRequest().authenticated() // Còn lại phải login
                );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}



//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
//    http
//            .csrf(AbstractHttpConfigurer::disable)
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(auth -> auth
//                    // 1. Cho phép Đăng ký (POST /api/users) không cần token
//                    .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
//
//                    // 2. Các endpoint xác thực và kiểm tra hệ thống vẫn mở tự do
//                    .requestMatchers("/api/auth/**", "/api/healthCheck/**").permitAll()
//
//                    // 3. Các hành động khác của User (GET, PUT, DELETE) bắt buộc phải có token
//                    .requestMatchers("/api/users/**").authenticated()
//
//                    // 4. Tất cả các request khác cũng phải login
//                    .anyRequest().authenticated()
//            );
//
//    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    return http.build();
//}