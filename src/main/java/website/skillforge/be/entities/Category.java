package website.skillforge.be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = false, nullable = false)
    String name;
    String description;

    @OneToMany(mappedBy = "category")
    List<Course> course;
}

