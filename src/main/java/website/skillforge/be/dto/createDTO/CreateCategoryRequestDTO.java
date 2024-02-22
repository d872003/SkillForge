package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCategoryRequestDTO {

    private String name;
    private String code;
    private String description;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;

}
