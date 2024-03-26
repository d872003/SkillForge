package website.skillforge.be.dto.createDTO;

import lombok.Data;

import java.util.List;

@Data
public class ChartResponse {
    List<String> labels;
    List<Integer> numOfStudents;
    List<Double> revenue;
}
