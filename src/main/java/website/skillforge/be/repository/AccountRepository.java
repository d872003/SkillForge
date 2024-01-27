package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import website.skillforge.be.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
