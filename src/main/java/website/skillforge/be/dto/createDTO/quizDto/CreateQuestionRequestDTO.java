package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;

@Data
public class CreateQuestionRequestDTO {

    private Integer questionNumber;
    private String questionContent;
    private long quiz_id;

}
