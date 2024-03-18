package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @JoinColumn(name = "order_detail_id")
    @JsonIgnore
    OrderDetail orderDetail;
}
