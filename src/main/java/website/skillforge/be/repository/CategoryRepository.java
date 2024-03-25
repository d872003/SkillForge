package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.courses.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(long id);
    Category findCategoryByName(String name);

}
