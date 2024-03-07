package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class GetAllQuizResponse {
    private long id;
    private String name;
    private String description;
    private long lesson_id;
}
