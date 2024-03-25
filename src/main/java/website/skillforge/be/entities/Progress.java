package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private long courseId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
