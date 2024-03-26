package website.skillforge.be.dto.createDTO;

import lombok.Data;

@Data
public class ChartRequest {
    private int month;
    private int year;
    private int interval;
}
