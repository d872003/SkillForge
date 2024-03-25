package website.skillforge.be.entities.courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.accounts.Account;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String description;
    boolean isFreeChapter;

    private Date createdDate;
    private Date lastUpdatedDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Lesson> lesson;
}
