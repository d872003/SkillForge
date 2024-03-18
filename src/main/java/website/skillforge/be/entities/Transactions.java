package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.ordered.OrderedDetail;

@Entity
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    double money;

    @ManyToOne()
    @JoinColumn(name = "from_id")
    Wallet from;

    @ManyToOne()
    @JoinColumn(name = "to_id")
    Wallet to;

    @ManyToOne
    @JoinColumn(name = "ordered_detail_id")
    @JsonIgnore
    OrderedDetail orderedDetail;
}
