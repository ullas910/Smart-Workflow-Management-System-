package Smart.Work.Force.Management.System.demo.auth.dto;

import Smart.Work.Force.Management.System.demo.auth.model.User;
import lombok.Data;

@Data
public class RegisterResponse {

    private Long id;
    private String username;
    private String message;

    public RegisterResponse(User user1) {
        this.id = user1.getId();
        this.username = user1.getUsername();
        this.message = "User Registered";
    }
}

