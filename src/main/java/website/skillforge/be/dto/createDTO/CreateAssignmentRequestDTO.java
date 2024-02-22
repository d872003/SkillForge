package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAssignmentRequestDTO {

    private String name;
    private String description;
    LocalDate createdDate;
    LocalDate lastUpdatedDate;
    private long lesson_id;
}
