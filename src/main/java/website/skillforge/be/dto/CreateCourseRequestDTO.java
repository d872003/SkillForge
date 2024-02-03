package website.skillforge.be.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCourseRequestDTO {

    private String name;
    private double price;
    private int totalOfChapter;
    private String pictureLink;
    private String description;
    private String status;
    private Date createdDate;
    private long categoryId;

}
