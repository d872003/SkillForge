package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateLessonRequestDTO {
    private String name;
    private String description;
//    private String videoLink;
    private long chapter_id;
}
