package website.skillforge.be.dto.authenticationDTO;

import lombok.Data;
import website.skillforge.be.enums.Role;

@Data
public class LoginResponseDTO {
    String username;
    String fullName;
    String email;
    String phone;
    Role role;
    String token;
}
