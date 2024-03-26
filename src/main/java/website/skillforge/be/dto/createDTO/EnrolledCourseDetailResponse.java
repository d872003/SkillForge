package website.skillforge.be.dto.createDTO;

import lombok.Data;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.entities.courses.Category;
import website.skillforge.be.entities.courses.Chapter;

import java.util.List;

@Data
public class EnrolledCourseDetailResponse {
    private Long id;
    private String name;
    private double price;
    private String code;
    private String pictureLink;
    private String description;
    private Category category;
    private Account createBy;
    private List<Chapter> chapters;
    private List<GetAllLessonResponse> lessons;
    //filed for Profile
    private boolean fineshed = false;
}
