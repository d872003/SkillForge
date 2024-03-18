package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.OrderStatus;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter

public class OrderDetail {
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

    @OneToMany(mappedBy = "orderDetail")
    List<Transactions> transactions; // <>
}
