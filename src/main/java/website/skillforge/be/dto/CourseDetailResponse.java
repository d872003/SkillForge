package website.skillforge.be.dto;

import lombok.Data;
import website.skillforge.be.entities.Chapter;
import website.skillforge.be.entities.Lesson;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.List;

@Data
public class CourseDetailResponse {
    private String name;
    private double price;
    private String pictureLink;
    private String description;
    private long categoryId;
    private String categoryName;


}
