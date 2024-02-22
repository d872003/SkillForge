package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int answerNumber;
    @Column(columnDefinition = "nvarchar(255)")
    private String answerContent;
    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;
}
