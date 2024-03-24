package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderedDTO {
    private Long totalPrice;
    private List<Long> courseId;
}
