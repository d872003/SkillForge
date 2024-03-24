package website.skillforge.be.dto.createDTO;

import lombok.Data;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.List;

@Data
public class GetAllLessonResponse {
    private long id;
    private String name;
    private String description;
    private String videoLink;
    private boolean isCompleted;
    private Quiz quiz;
    private long chapter_id;
}
