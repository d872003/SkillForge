package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;
import website.skillforge.be.entities.quiz.OldQuizQuestion;
import website.skillforge.be.entities.quiz.QuizResult;

import java.util.Date;
import java.util.List;

@Data
public class CreateQuizResultRequestDto {
    private QuizResult quizResult;
    private List<Long> falseQuestionIds;
    private List<Long> falseAnswerIds;
    private List<Long> trueAnswerIds;
}
