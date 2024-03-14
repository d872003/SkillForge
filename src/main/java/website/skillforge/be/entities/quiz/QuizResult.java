package website.skillforge.be.entities.quiz;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.Account;


import java.util.Date;

@Entity
@Getter
@Setter
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double score;

    private int trueAnswerNumber;

    private int falseAnswerNumber;

    private Date date;

    @OneToOne
    @JoinColumn(name = "quiz_id")
    Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account doBy;


}
