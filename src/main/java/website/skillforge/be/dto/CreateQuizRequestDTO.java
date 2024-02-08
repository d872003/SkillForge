package website.skillforge.be.dto;

import lombok.Data;

@Data
public class CreateQuizRequestDTO {

    private String name;
    private String description;
    private long lesson_id;
}
