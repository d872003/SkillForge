package website.skillforge.be.dto.updateDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCourseDTO {
    private String name;
    private double price;
    private String pictureLink;
    private String description;
    private LocalDate lastUpdatedDate;
    private long categoryId;
}
