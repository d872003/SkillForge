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
    @Column(columnDefinition = "nvarchar(255)")
    private String answerContent;
    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "quizQuestion_id")
    @JsonIgnore
    private QuizQuestion quizQuestion;
}