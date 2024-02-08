package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    int totalOfQuiz;
    int totalOfAssignment;
    @Column(columnDefinition = "nvarchar(255)")
    String description;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private List<Quiz> quiz;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private List<Assignment> assignment;
}
