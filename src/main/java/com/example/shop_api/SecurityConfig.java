package com.example.shop_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Tạm tắt CSRF cho tiện dev
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // CHO PHÉP truy cập h2-console không cần login
                        .requestMatchers("/h2-console/**").permitAll()
                        // CHO PHÉP swagger
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // CHO PHÉP các API auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        // Các request còn lại cũng cho qua luôn (dev)
                        .anyRequest().permitAll()
                )
                // Cho H2 console hiển thị trong frame
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                // Giữ httpBasic cũng được, nhưng do tất cả permitAll nên nó không hiện popup nữa
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
