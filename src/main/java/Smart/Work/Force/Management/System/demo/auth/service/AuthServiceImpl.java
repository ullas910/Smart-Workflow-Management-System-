package Smart.Work.Force.Management.System.demo.auth.service;

import Smart.Work.Force.Management.System.demo.auth.dto.LoginRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterResponse;
import Smart.Work.Force.Management.System.demo.auth.model.User;
import Smart.Work.Force.Management.System.demo.auth.repository.UserRepository;
import Smart.Work.Force.Management.System.demo.auth.security.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // Added for registration
    private final PasswordEncoder passwordEncoder; // Added for registration

    // Constructor Injection of all required dependencies
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // 1. Create User entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail()); // Assuming RegisterRequest has an email field

        // 2. Encode password (using a PasswordEncoder bean)
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        // **IMPORTANT**: You should also set the user's role(s) here.
        // user.setRoles(Set.of(roleRepository.findByName("USER")));

        // 3. Save to UserRepository
        userRepository.save(user);

        // 4. Return response
        return new RegisterResponse("User registered successfully! Username: " + user.getUsername());
    }

    @Override
    public String login(LoginRequest request) {
        // 1. Authenticate the user using Spring Security's AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // If authentication fails, an exception (e.g., BadCredentialsException) is thrown
        // which Spring Security handles, resulting in a 401 response.

        // 2. If successful, get the UserDetails from the Authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 3. Generate the JWT (access token)
        String accessToken = jwtUtil.generateAccessToken(userDetails);

        // 4. Return the token
        return accessToken;
    }
}
