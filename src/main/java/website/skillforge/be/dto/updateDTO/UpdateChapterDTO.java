package website.skillforge.be.dto.updateDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateChapterDTO {
    private String name;
    private String description;
    private boolean isFreeChapter;
    private long course_id;
    private LocalDate lastUpdatedDate;
}
