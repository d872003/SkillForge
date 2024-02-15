package website.skillforge.be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateAssignmentRequestDTO {

    private String name;
    private String description;
    private Date createdDate;
    private long lesson_id;
}
