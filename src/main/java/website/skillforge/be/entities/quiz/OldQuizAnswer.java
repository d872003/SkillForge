package website.skillforge.be.entities.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OldQuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int answerNumber;
    private double answerScore;
    @Column(columnDefinition = "nvarchar(255)")
    private String answerContent;
    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "oldQuestionId")
    @JsonIgnore
    private OldQuizQuestion oldQuizQuestion;
}
