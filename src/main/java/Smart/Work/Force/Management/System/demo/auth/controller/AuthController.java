package Smart.Work.Force.Management.System.demo.auth.controller;

import Smart.Work.Force.Management.System.demo.auth.service.AuthService;
import Smart.Work.Force.Management.System.demo.auth.dto.LoginRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.LoginResponse;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }




    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // 1. Authenticate user credentials via AuthService
        // 2. Generate the JWT access token if successful

        // 3. Return the token in the response DTO
        return ResponseEntity.ok(authService.login(request));
    }
    @GetMapping("/admin/ping")
    public String adminPing(){
        return "ok-admin";
    }

    @GetMapping("/manager/ping")
    public String managerPing(){
        return "ok-manager";
    }

    @GetMapping("/employee/ping")
    public String employeePing(){
        return "ok-employee";
    }
}
