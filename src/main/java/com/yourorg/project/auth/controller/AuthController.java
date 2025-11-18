package com.yourorg.project.auth.controller;

import com.yourorg.project.auth.dto.RegisterRequest;
import com.yourorg.project.auth.dto.RegisterResponse;
import com.yourorg.project.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
