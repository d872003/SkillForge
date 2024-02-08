package website.skillforge.be.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import website.skillforge.be.dto.LoginRequestDTO;
import website.skillforge.be.dto.LoginResponseDTO;
import website.skillforge.be.dto.RegisterRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.repository.AccountRepository;
import website.skillforge.be.util.TokenHandler;

@Service
public class AuthenticationService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenHandler tokenHandler;
    public Account register(RegisterRequestDTO registerRequestDTO) {
        Account account = new Account();
        String rawPassword = registerRequestDTO.getPassword();
        account.setUsername(registerRequestDTO.getUsername());
        account.setPassword(passwordEncoder.encode(rawPassword));
        account.setEmail(registerRequestDTO.getEmail());
        account.setFullName(registerRequestDTO.getFullName());
        account.setAvatar(registerRequestDTO.getAvatar());
        account.setRole(registerRequestDTO.getRole());
        account.setPhone(registerRequestDTO.getPhone());
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
            Account account = (Account) authentication.getPrincipal();
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(tokenHandler.generateToken(account));
            loginResponseDTO.setUsername(account.getUsername());
            loginResponseDTO.setFullName(account.getFullName());
            loginResponseDTO.setEmail(account.getEmail());
            loginResponseDTO.setPhone(account.getPhone());
            loginResponseDTO.setRole(account.getRole());
            loginResponseDTO.setStatus(account.getStatus());

            return loginResponseDTO;
        }catch(Exception e) {
            throw new InternalAuthenticationServiceException("Authentication failed: " + e.getMessage());
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
