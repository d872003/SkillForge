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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account doBy;


}
