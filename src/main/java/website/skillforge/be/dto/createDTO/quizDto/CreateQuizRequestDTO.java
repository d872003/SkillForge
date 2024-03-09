package website.skillforge.be.dto.createDTO.quizDto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateQuizRequestDTO {

    private String name;
    private String description;
    private long lesson_id;
}
