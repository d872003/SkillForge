package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class CreateQuestionRequestDTO {

    private Integer questionNumber;
    private String description;
    private long quiz_id;

}
