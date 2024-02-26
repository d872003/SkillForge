package website.skillforge.be.dto.updateDTO;

import lombok.Data;
import java.util.Date;

@Data
public class UpdateCriteriaDTO {
    private String name;
    private String description;
    private Date lastUpdateDate;
    private long maxScore;
    private long rubricId;
}
