package website.skillforge.be.dto.createDTO;

import lombok.Data;
import website.skillforge.be.dto.createDTO.GetAllLessonResponse;
import website.skillforge.be.dto.createDTO.quizDto.GetAllQuizResponse;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Category;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.List;

@Data
public class CourseDetailResponse {
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
}
