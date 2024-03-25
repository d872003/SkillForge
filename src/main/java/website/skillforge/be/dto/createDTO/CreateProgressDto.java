package website.skillforge.be.dto.createDTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class CreateProgressDto {
    private Long lessonId;

}
