package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class CreateCriterionRequestDTO {
    private String name;
    private String description;
    private long rubric_id;
}
