package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.CourseStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Long id;
    @Column(columnDefinition = "nvarchar(255)")
    String name;
    double price;
    @Column(columnDefinition = "nvarchar(255)")
    String pictureLink;
    @Column(columnDefinition = "nvarchar(255)")
    String description;
    LocalDate createdDate;
    LocalDate lastUpdatedDate;
    @Enumerated(EnumType.STRING)
    CourseStatus courseStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Chapter> chapter;
}
