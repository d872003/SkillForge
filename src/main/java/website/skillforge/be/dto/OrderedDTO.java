package website.skillforge.be.dto;

import lombok.Data;

@Data
public class OrderedDTO {
    Double totalPrice;
    Long courseId;
    String AdditionalNotes;
    
}
