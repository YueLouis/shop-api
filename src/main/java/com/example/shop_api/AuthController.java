package com.example.shop_api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        // Demo: hard-code user
        if ("admin".equals(request.getUsername())
                && "123456".equals(request.getPassword())) {

            String token = jwtService.generateToken(request.getUsername());
            TokenResponse resp = new TokenResponse();
            resp.setToken(token);
            return ResponseEntity.ok(resp);
        }
        return ResponseEntity.status(401).build();
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class TokenResponse {
        private String token;
    }
}
