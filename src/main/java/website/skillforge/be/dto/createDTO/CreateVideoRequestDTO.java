package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class CreateVideoRequestDTO {
    private String videoLink;
    private String videoName;
    private String uploadBy;
    private Long chapterId;
    private Long lessonId;
}
