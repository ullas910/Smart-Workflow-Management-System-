package com.yourorg.project.auth.service;

import com.yourorg.project.auth.dto.RegisterRequest;
import com.yourorg.project.auth.dto.RegisterResponse;
import com.yourorg.project.auth.model.Role;
import com.yourorg.project.auth.model.User;
import com.yourorg.project.auth.repository.RoleRepository;
import com.yourorg.project.auth.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request) {

        // Validate unique email/username
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Fetch role from DB (ROLE_ADMIN, ROLE_MANAGER, etc.)
        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Invalid Role"));

        // Create User
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.getRoles().add(role);

        User saved = userRepository.save(user);

        return new RegisterResponse(saved.getId(), saved.getUsername(), "User Registered Successfully");
    }


}
