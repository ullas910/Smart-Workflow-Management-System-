package Smart.Work.Force.Management.System.demo.auth.controller;

import Smart.Work.Force.Management.System.demo.auth.service.AuthService;
import Smart.Work.Force.Management.System.demo.auth.dto.LoginRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.LoginResponse;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    // Dependency Injection via Constructor
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Handles user registration.
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // 1. Authenticate user credentials via AuthService
        // 2. Generate the JWT access token if successful
        String jwtAccessToken = authService.login(request);

        // 3. Return the token in the response DTO
        return ResponseEntity.ok(new LoginResponse(jwtAccessToken));
    }
}
