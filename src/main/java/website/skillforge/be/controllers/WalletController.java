package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import website.skillforge.be.entities.Transactions;
import website.skillforge.be.entities.Wallet;
import website.skillforge.be.enums.Role;
import website.skillforge.be.repository.TransactionRepository;
import website.skillforge.be.repository.WalletRepository;
import website.skillforge.be.util.AccountUtil;

import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class WalletController {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountUtil accountUtils;

    @GetMapping("/wallet")
    public Wallet getWallet() {
        if (accountUtils.getCurrentAccount().getRole() == Role.ADMIN) {
            return walletRepository.findWalletByAccount_role(Role.ADMIN);
        } else {
            if (accountUtils.getCurrentAccount().getRole() == Role.TEACHER) {
                return walletRepository.findWalletByAccount_role(Role.TEACHER);
            } else {
                return walletRepository.findWalletByAccount_role(Role.STUDENT);
            }
        }
    }

    @GetMapping("/wallet/transactions")
    public List<Transactions> getTransaction() {
        if (accountUtils.getCurrentAccount().getRole() == Role.ADMIN) {
            return transactionRepository.findTransactionsByTo_Account_role(Role.ADMIN);
        } else {
            if (accountUtils.getCurrentAccount().getRole() == Role.TEACHER) {
                return transactionRepository.findTransactionsByTo_Account_role(Role.TEACHER);
            } else {
                return transactionRepository.findTransactionsByTo_Account_role(Role.STUDENT);
            }
        }
    }

}
