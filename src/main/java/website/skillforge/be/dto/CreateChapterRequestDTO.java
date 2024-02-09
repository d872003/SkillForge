package website.skillforge.be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateChapterRequestDTO {

    private String name;
    private String description;
    private boolean isFreeChapter;
    private long course_id;
    private Date createdDate;
}
