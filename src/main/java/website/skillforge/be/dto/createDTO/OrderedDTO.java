package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderedDTO {
    Long totalPrice;
    List<Long> courseId;
}
