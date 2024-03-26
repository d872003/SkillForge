package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.enums.Role;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Account findAccountByEmail(String email);

    Account findAccountById(Long id);
    Account findAccountByUsername(String username);

    Account findByEmail(String email);

    Account findAccountByRole(Role role);
}
