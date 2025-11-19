package Smart.Work.Force.Management.System.demo.auth.dto;

public class RegisterResponse {

    private Long id;
    private String username;
    private String message;

    public RegisterResponse(Long id, String username, String message) {
        this.id = id;
        this.username = username;
        this.message = message;
    }

    // Getters
    public Long getId()
    {
        return id;
    }
    public String getUsername()
    {
        return username;
    }
    public String getMessage()
    {
        return message;
    }
}

