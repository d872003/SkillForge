package website.skillforge.be.dto;

import lombok.Data;

@Data
public class CreateCategoryRequestDTO {
    private Long id;
    private String name;
    private String description;

}
