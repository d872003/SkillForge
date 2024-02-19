package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class CreateAnswerRequestDTO {

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Boolean isTrue;
    private String questionId;

}
