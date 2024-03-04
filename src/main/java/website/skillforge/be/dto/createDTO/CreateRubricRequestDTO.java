package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateRubricRequestDTO {

    private String name;
    private String description;
    private long assignment_id;
}
