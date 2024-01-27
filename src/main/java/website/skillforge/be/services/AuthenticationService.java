package website.skillforge.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import website.skillforge.be.entities.Account;
import website.skillforge.be.repository.AccountRepository;

@Service
public class AuthenticationService {
    @Autowired
    AccountRepository accountRepository;
    public Account register(Account account) {
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }
}
