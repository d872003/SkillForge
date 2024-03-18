package website.skillforge.be.dto.authenticationDTO;

import lombok.Data;
import website.skillforge.be.dto.createDTO.CourseDetailResponse;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Course;

import java.util.List;

@Data
public class ProfileResponseDTO {
    Account account;
    List<CourseDetailResponse> courseDetailResponse;
}
