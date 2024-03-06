package website.skillforge.be.dto;

import lombok.Data;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Category;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.List;

@Data
public class CourseDetailResponse {
    Long id;
    private String name;
    private double price;
    private String code;
    private String pictureLink;
    private String description;
    private Category category;
    private Account createBy;
    List<Chapter> chapters;
    List<Lesson> lessons;
    List<Quiz> quizzes;


}
