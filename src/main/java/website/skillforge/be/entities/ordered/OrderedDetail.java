package website.skillforge.be.entities.ordered;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.Account;
import website.skillforge.be.entities.Transactions;
import website.skillforge.be.enums.status.OrderStatus;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter

public class OrderedDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date createdDate;
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    double price;
    Long courseId;

    @ManyToOne
    @JoinColumn(name = "ordered_id")
    private Ordered ordered;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "orderedDetail")
    List<Transactions> transactions; // <>
}
