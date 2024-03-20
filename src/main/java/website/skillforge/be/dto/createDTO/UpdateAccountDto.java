package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class UpdateAccountDto {
    private String password;
    private String email;
    private String avatar;
    private String fullName;
    private String phone;
}
