package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class GetAllLessonResponse {
    private long id;
    private String name;
    private String description;
    private String videoLink;
    private long chapter_id;
}
