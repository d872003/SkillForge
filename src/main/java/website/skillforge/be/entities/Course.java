package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.enums.status.CourseStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    double price;
    private String code;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String pictureLink;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String description;
    private Date createdDate;
    private Date lastUpdatedDate;
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Chapter> chapter;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CourseEnrollment> courseEnrollment;
}
