package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Setter
@Getter

public class Course {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    long id;

    String name;
    double price;
    int totalOfChapter;
    String pictureLink;
    String description;
    Date createdDate;
    String status;

    @ManyToOne
    @JoinColumn(name = "id")
    private Category category;
}
