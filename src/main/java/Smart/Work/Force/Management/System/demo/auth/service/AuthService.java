package Smart.Work.Force.Management.System.demo.auth.service;

import Smart.Work.Force.Management.System.demo.auth.dto.LoginRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.LoginResponse;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}
