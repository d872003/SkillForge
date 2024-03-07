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
    OrderStatus status;
    Double totalPrice;
    Long courseId;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private String AdditionalNotes;


}
