package website.skillforge.be.dto.authenticationDTO;

import lombok.Data;
import website.skillforge.be.enums.Role;
import website.skillforge.be.enums.status.AccountStatus;

@Data
public class LoginResponseDTO {
    String username;
    String fullName;
    String email;
    String phone;
    Role role;
    AccountStatus status;
    String token;
}
