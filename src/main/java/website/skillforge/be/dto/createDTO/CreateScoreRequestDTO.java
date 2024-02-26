package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.util.Date;

@Data
public class CreateScoreRequestDTO {
    private int Score;
    private Date submissionDate;
    private String commit;
    private long rubric_id;
    private long criteria_id;
}
