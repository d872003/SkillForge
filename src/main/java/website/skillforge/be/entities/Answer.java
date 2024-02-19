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

    @Column(columnDefinition = "nvarchar(255)")
    private String answer1;
    @Column(columnDefinition = "nvarchar(255)")
    private String answer2;
    @Column(columnDefinition = "nvarchar(255)")
    private String answer3;
    @Column(columnDefinition = "nvarchar(255)")
    private String answer4;

    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;
}
