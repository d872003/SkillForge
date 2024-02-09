package website.skillforge.be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateQuizRequestDTO {

    private String name;
    private String description;
    private long lesson_id;
    private Date createdDate;
}
