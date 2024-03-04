package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateCategoryRequestDTO {

    private String name;
    private String code;
    private String description;

}
