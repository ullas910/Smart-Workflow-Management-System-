package Smart.Work.Force.Management.System.demo.auth.service;

import Smart.Work.Force.Management.System.demo.auth.repository.UserRepository;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterResponse;
import Smart.Work.Force.Management.System.demo.auth.model.Role;
import Smart.Work.Force.Management.System.demo.auth.model.User;
import Smart.Work.Force.Management.System.demo.auth.repository.RoleRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
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

//         Fetch role from DB (ROLE_ADMIN, ROLE_MANAGER, etc.)
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
