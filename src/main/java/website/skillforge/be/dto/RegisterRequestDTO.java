package website.skillforge.be.dto;

import lombok.Data;
import website.skillforge.be.enums.Role;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private Role role;
}
