package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.CourseStatus;

import java.util.Date;

@Entity
@Setter
@Getter

public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    Long courseId;
    CourseStatus status;
    Date startDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;
}
