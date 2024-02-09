package website.skillforge.be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateLessonRequestDTO {
    private String name;
    private String description;
    private String videoLink;
    private long chapter_id;
    private Date createdDate;
}
