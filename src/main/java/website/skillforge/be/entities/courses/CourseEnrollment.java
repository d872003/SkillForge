package website.skillforge.be.entities.courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.enums.status.EnrollStatus;

import java.util.Date;

@Entity
@Setter
@Getter

public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnrollStatus status;
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
}
