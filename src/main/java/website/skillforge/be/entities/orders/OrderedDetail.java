package website.skillforge.be.entities.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.entities.accounts.Transactions;
import website.skillforge.be.enums.status.OrderStatus;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter

public class OrderedDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private double price;
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "ordered_id")
    private Ordered ordered;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "orderedDetail")
    private List<Transactions> transactions; // <>
}
