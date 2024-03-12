package website.skillforge.be.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import website.skillforge.be.entities.Account;

@Component
public class AccountUtil {
    public Account getCurrentAccount() {
        try {
            return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
