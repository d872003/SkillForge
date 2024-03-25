package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAnswer {
    @Id
    private Long answerId;
    private Long quizId;
    private boolean isTrue;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
