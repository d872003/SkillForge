package website.skillforge.be.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoriesId;
    private String categoriesName;
    private String categoriesType;
    private String categoriesDescription;
}
