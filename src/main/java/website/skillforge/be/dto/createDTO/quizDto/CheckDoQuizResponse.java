package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;
import website.skillforge.be.entities.quiz.Quiz;

@Data
public class CheckDoQuizResponse {
    Quiz quiz;
    Boolean isDo;
}
