package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.Categories;
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
