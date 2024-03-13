package website.skillforge.be.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderedDTO {
    Double totalPrice;
    String currency;
    Long courseId;
    String method;
    String intent;
    String AdditionalNotes;

//    private double price;
//    private String currency;
//    private String method;
//    private String intent;
//    private String description;
}
