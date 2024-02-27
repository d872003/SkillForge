package website.skillforge.be.entities.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class OldQuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private int questionNumber;
    @Column(columnDefinition = "nvarchar(255)")
    private String questionContent;
    private double questionScore;

    @ManyToOne
    @JoinColumn(name = "oldQuiz_id")
    @JsonIgnore
    private OldQuiz oldQuiz;

    @OneToMany(mappedBy = "oldQuizQuestion")
    @JsonIgnore
    private List<OldQuizAnswer> oldQuizAnswers;
}
