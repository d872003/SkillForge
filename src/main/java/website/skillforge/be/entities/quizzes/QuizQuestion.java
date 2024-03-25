package website.skillforge.be.entities.quizzes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Entity
@Getter
@Setter
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String questionNumber;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String questionContent;
    double questionScore;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    @OneToMany(mappedBy = "quizQuestion", cascade = CascadeType.ALL)
    private List<QuizAnswer> quizAnswers;
}
