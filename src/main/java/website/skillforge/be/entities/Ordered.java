package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.OrderStatus;

import java.util.Date;

@Entity
@Setter
@Getter

public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date createdDate;
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    Long totalPrice;
    Long courseId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
