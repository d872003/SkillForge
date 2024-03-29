package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCourseRequestDTO {

    private String name;
    private double price;
    private String code;
    private String pictureLink;
    private String description;
    private long categoryId;

}
