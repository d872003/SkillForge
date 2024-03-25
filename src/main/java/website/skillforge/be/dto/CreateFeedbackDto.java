package website.skillforge.be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateFeedbackDto {

    private String content;
    private int star;
    private Date createdDate;
    private Long courseId;
}
