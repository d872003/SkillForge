package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.orders.Ordered;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Long> {
    Ordered findOrderedById(long id);

    Ordered findOrderedByAccount_id(long account_id);
}
