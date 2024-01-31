package website.skillforge.be.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private enum Role {
        STUDENT,
        ADMIN,
        TEACHER
    }
}
