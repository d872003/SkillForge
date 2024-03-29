package website.skillforge.be.dto.createDTO;

import lombok.Data;
import website.skillforge.be.entities.quizzes.Quiz;

@Data
public class GetAllLessonResponse {
    private long id;
    private String name;
    private String description;
    private String videoLink;

    private boolean isCompleted;

    public void SetIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    private Quiz quiz;
    private long chapter_id;
}
