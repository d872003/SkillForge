package website.skillforge.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.skillforge.be.entities.accounts.Wallet;
import website.skillforge.be.enums.Role;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findWalletByAccount_role(Role role);
}
