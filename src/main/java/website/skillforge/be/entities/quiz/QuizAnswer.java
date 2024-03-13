package website.skillforge.be.entities.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int answerNumber;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String answerContent;
    private double answerScore;

    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "quizQuestion_id")
    @JsonIgnore
    private QuizQuestion quizQuestion;
}
