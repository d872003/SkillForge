package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Setter
@Getter
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Long id;

    @Column(columnDefinition = "nvarchar(255)")
    String name;

    @Column(columnDefinition = "nvarchar(255)")
    String description;

    Date createdDate;
    Date lastUpdateDate;
    long MaxScore;

    @ManyToOne
    @JoinColumn (name = "rubric_id")
    private Rubric rubric;


}
