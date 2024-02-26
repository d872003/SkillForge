package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCriteriaRequestDTO {
    private String name;
    private String description;
    private Date createdDate;
    private long maxScore;
    private long rubricId;
}
