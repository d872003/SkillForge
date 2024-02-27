package website.skillforge.be.dto.updateDTO;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateScoreDTO {

    private int score;
    private Date submissionDate;
    private String comment;
    private long rubric_id;
    private long criteria_id;
}
