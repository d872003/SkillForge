package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateQuizResultRequestDto {

    private int score;
    private int trueAnswerNumber;
    private int falseAnswerNumber;
    private Date date;

}
