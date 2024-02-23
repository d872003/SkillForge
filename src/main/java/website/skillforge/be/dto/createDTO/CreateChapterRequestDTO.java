package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateChapterRequestDTO {

    private String name;
    private String description;
    private boolean isFreeChapter;
    private long course_id;

}
