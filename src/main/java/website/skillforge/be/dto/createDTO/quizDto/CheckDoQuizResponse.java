package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.List;

@Data
public class CheckDoQuizResponse {
    private Quiz quiz;
    private Boolean isDo;
    private List<Long> answerUser;
    private List<Long> falseAnswerIds;
    private List<Long> trueAnswerIds;
}
