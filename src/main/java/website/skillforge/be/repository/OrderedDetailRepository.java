package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.orders.OrderedDetail;

import java.util.List;

@Repository
public interface OrderedDetailRepository extends JpaRepository<OrderedDetail, Long> {

    OrderedDetail findOrderedDetailById(long id);

    List<OrderedDetail> findOrderedDetailByOrderedId(long orderedId);

}
