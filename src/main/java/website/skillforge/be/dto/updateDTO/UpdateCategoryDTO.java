package website.skillforge.be.dto.updateDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCategoryDTO {
    private String name;
    private String code;
    private String description;
    private LocalDate lastUpdatedDate;
}
