package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.util.Date;

@Data
public class CreateScoreRequestDTO {

    private Integer score;
    private Date submissionDate;
    private String comment;
    private long rubric_id;
    private long criteria_id;
}
