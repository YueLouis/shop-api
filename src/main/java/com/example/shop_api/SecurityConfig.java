package com.example.shop_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF cho API REST
                .csrf(csrf -> csrf.disable())
                // Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        // Cho phép tự do truy cập các API của bài làm
                        .requestMatchers(
                                "/api/**",
                                "/auth/**",
                                "/graphql",
                                "/graphiql",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Các URL khác (nếu có) mới cần auth
                        .anyRequest().permitAll()
                );

        // Tạm thời chưa ép dùng JWT filter, nên không add filter gì thêm
        return http.build();
    }
}
