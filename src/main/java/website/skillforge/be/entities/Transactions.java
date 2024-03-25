package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.ordered.OrderedDetail;

import java.util.Date;

@Entity
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double money;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "from_id")
    private Wallet from;

    @ManyToOne()
    @JoinColumn(name = "to_id")
    private Wallet to;

    @ManyToOne
    @JoinColumn(name = "ordered_detail_id")
    @JsonIgnore
    private OrderedDetail orderedDetail;
}
