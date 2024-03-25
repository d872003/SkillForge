package website.skillforge.be.entities.courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.entities.quizzes.Quiz;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "nvarchar(255)")
    String name;
    @Column(columnDefinition = "varchar(MAX)")
    String videoLink;
    @Column(columnDefinition = "nvarchar(MAX)")
    String description;
    Date createdDate;
    Date lastUpdatedDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account createBy;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Quiz quiz;
    @OneToMany(mappedBy = "lesson")
    private List<Progress> progresses;
}
