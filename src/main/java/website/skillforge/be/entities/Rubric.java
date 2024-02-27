package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "nvarchar(255)")
    String name;
    @Column(columnDefinition = "nvarchar(255)")
    String description;

    Date createDate;
    Date lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @OneToMany(mappedBy = "rubric")
    @JsonIgnore
    private List<Criteria> criteria;
}

