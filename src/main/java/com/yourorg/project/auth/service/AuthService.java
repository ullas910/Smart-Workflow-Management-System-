package com.yourorg.project.auth.service;

import com.yourorg.project.auth.dto.RegisterRequest;
import com.yourorg.project.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

}
