package website.skillforge.be.dto;

import lombok.Data;

@Data
public class CreateCategoryRequestDTO {

    private String name;
    private String code;
    private String description;

}
