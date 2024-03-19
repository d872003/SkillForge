package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAnswer {
    @Id
    Long answerId;
    Long quizId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

}
