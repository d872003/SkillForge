package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Setter
@Getter
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Long id;

    int score;

    Date SubmissionDate;

    @Column(columnDefinition = "nvarchar(255)")
    String comment;

    @ManyToOne
    @JoinColumn (name = "rubric_id")
    private Rubric rubric;

    @OneToOne
    @JoinColumn (name = "criteria_id")
    private Criteria criteria;
}
