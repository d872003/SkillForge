package website.skillforge.be.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import website.skillforge.be.enums.Role;

@Data
public class LoginResponseDTO {
    String username;
    String fullName;
    String email;
    String phone;
    Role role;
    String status;
    String token;
}
