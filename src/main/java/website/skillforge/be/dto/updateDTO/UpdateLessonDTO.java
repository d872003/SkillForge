package website.skillforge.be.dto.updateDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateLessonDTO {
    private String name;
    private String description;
    private String videoLink;
    private long chapter_id;
    private LocalDate lastUpdatedDate;
}
