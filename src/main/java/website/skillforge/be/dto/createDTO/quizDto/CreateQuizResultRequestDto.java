package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;
import website.skillforge.be.entities.quizzes.QuizResult;

import java.util.List;

@Data
public class CreateQuizResultRequestDto {
    private QuizResult quizResult;
    private List<Long> falseQuestionIds;
    private List<Long> falseAnswerIds;
    private List<Long> trueAnswerIds;
}
