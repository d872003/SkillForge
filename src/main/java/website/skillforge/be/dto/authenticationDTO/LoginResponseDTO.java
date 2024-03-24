package website.skillforge.be.dto.authenticationDTO;

import lombok.Data;
import website.skillforge.be.enums.Role;
import website.skillforge.be.enums.status.AccountStatus;

@Data
public class LoginResponseDTO {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Role role;
    private AccountStatus status;
    private String token;
}
