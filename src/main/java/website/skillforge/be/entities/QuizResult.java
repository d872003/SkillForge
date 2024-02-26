package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Entity
@Getter
@Setter
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private int trueAnswerNumber;

    private int falseAnswerNumber;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account doBy;


}
