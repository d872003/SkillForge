package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;

    @Column(columnDefinition = "nvarchar(255)")
    private String description;

    Date createDate;
    Date lastUpdatedDate;


    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @OneToMany(mappedBy = "rubric")
    @JsonIgnore
    private List<Criterion> criterionList;
}

