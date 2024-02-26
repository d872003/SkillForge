package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class CreateCriteriaRequestDTO {
    private String name;
    private String description;
    private long maxScore;
    private String rubricId;
}
