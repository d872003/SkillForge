package website.skillforge.be.dto.authenticationDTO;

import lombok.Data;
import website.skillforge.be.dto.createDTO.CourseDetailResponse;
import website.skillforge.be.entities.accounts.Account;

import java.util.List;

@Data
public class ProfileResponseDTO {
    private Account account;
    private List<CourseDetailResponse> courseDetailResponse;
}
