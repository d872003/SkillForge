package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.assignment.Assignment;
import website.skillforge.be.entities.quiz.Quiz;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "nvarchar(255)")
    String name;
    String videoLink;
    @Column(columnDefinition = "nvarchar(255)")
    String description;
    Date createdDate;
    Date lastUpdatedDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private List<Quiz> quiz;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private List<Assignment> assignment;
}
