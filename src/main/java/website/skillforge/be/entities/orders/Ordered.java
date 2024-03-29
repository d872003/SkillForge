package website.skillforge.be.entities.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.enums.status.OrderStatus;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "ordered")
    private List<OrderedDetail> orderedDetails;
}
