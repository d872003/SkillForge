package website.skillforge.be.dto.updateDTO;

import java.util.Date;
import lombok.Data;
@Data
public class UpdateRubricDTO {
    private String name;
    private String description;
    private Date lastUpdatedDate;
    private long assignment_id;
}
