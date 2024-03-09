package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;

@Data
public class CreateAnswerRequestDTO {
    private int answerNumber;
    private String answerContent;
    private Boolean isTrue;
    private long quizQuestion_id;

}
