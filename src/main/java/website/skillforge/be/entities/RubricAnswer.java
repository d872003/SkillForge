package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class RubricAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ansContent;
    private double score;
    private boolean isTrue;
    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;
}
