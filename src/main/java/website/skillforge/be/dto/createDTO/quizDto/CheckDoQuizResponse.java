package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;
import website.skillforge.be.dto.createDTO.UserAnswerDto;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.List;

@Data
public class CheckDoQuizResponse {
    Quiz quiz;
    Boolean isDo;
    List<UserAnswerDto> answerUser;
}
