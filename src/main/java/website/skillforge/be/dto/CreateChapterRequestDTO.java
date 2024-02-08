package website.skillforge.be.dto;

import lombok.Data;

@Data
public class CreateChapterRequestDTO {

    private String name;
    private String description;
    private boolean isFreeChapter;
    private long course_id;
}
