package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAssignmentRequestDTO {

    private String name;
    private String description;
    private long lesson_id;
}