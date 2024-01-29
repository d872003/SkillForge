package website.skillforge.be.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.LoginRequestDTO;
import website.skillforge.be.dto.RegisterRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.repository.AccountRepository;

@Service
public class AuthenticationService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    public Account register(RegisterRequestDTO registerRequestDTO) {
        Account account = new Account();
        String rawPassword = registerRequestDTO.getPassword();
        account.setUsername(registerRequestDTO.getUsername());
        account.setPassword(passwordEncoder.encode(rawPassword));
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }
    public Account login(LoginRequestDTO loginRequestDTO) {
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
            return (Account)authentication.getPrincipal();
        }catch(Exception e) {
            return null;
        }
    }
    public Account loginGoogle(String token) {
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String email = decodedToken.getEmail();
            Account account = accountRepository.findByEmail(email);
            return account;
        }catch(FirebaseAuthException e){
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }
}
