package Smart.Work.Force.Management.System.demo.auth.service;

import Smart.Work.Force.Management.System.demo.auth.dto.RegisterRequest;
import Smart.Work.Force.Management.System.demo.auth.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

}
