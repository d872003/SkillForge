package website.skillforge.be.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Setter
@Getter

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long courseId;
    String courseName;
    double price;
    int totalOfChapter;
    String coursePictureLink;
    String courseDescription;
    Date createdDate;
    String status;

}
