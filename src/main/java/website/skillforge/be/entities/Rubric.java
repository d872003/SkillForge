package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

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

    LocalDate createDate;
    LocalDate lastUpdatedDate;


    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
}

