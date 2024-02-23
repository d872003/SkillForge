package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String description;
    Date createdDate;
    Date lastUpdatedDate;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @OneToMany(mappedBy = "quiz")
    @JsonIgnore
    private List<Question> question;
}
