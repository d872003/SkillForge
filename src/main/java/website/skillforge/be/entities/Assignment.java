package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String description;
    LocalDate createdDate;
    LocalDate lastUpdatedDate;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @OneToOne(mappedBy = "assignment")
    @JsonIgnore
    private Rubric rubric;

}
