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
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "nvarchar(255)")
    String name;
    int totalOfLesson;
    @Column(columnDefinition = "nvarchar(255)")
    String description;
    boolean isFreeChapter;

    LocalDate createdDate;
    LocalDate lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToMany(mappedBy = "chapter")
    @JsonIgnore
    private List<Lesson> lesson;
}
