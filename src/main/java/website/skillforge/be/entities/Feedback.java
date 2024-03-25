package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String content;
    float star;
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
}
