package website.skillforge.be.entities.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class OldQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String description;
    private Date createdDate;
    private Date lastUpdatedDate;
    @ManyToOne
    @JoinColumn(name = "quizResult_id")
    private QuizResult quizResult;
    @OneToMany(mappedBy = "oldQuiz")
    @JsonIgnore
    private List<OldQuizQuestion> oldQuizQuestion;
}
