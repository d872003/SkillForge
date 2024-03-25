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
import website.skillforge.be.dto.authenticationDTO.LoginRequestDTO;
import website.skillforge.be.dto.authenticationDTO.LoginResponseDTO;
import website.skillforge.be.dto.authenticationDTO.ProfileResponseDTO;
import website.skillforge.be.dto.authenticationDTO.RegisterRequestDTO;
import website.skillforge.be.dto.createDTO.UpdateAccountDto;
import website.skillforge.be.entities.accounts.Account;
import website.skillforge.be.enums.status.AccountStatus;
import website.skillforge.be.repository.AccountRepository;
import website.skillforge.be.util.AccountUtil;
import website.skillforge.be.util.TokenHandler;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CourseService courseService;
    @Autowired
    TokenHandler tokenHandler;
    @Autowired
    AccountUtil accountUtil;

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
        account.setStatus(AccountStatus.ACTIVE);
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
            Account account = (Account) authentication.getPrincipal();
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(tokenHandler.generateToken(account));
            loginResponseDTO.setUsername(account.getUsername());
            loginResponseDTO.setFullName(account.getFullName());
            loginResponseDTO.setEmail(account.getEmail());
            loginResponseDTO.setPhone(account.getPhone());
            loginResponseDTO.setStatus(account.getStatus());
            loginResponseDTO.setRole(account.getRole());
            return loginResponseDTO;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("Authentication failed: " + e.getMessage());
        }
    }

    public LoginResponseDTO loginGoogle(String token) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String email = decodedToken.getEmail();
            Account account = accountRepository.findByEmail(email);
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(tokenHandler.generateToken(account));
            loginResponseDTO.setUsername(account.getUsername());
            loginResponseDTO.setFullName(account.getFullName());
            loginResponseDTO.setEmail(account.getEmail());
            loginResponseDTO.setPhone(account.getPhone());
            loginResponseDTO.setStatus(account.getStatus());
            loginResponseDTO.setRole(account.getRole());
            return loginResponseDTO;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findAccountById(id);
    }

    public ProfileResponseDTO getProfileById() {
        try {
            Account account = accountUtil.getCurrentAccount();
            ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
            profileResponseDTO.setAccount(account);
            profileResponseDTO.setCourseDetailResponse(courseService.getEnrollCourseDetail());
            return profileResponseDTO;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("Authentication failed: " + e.getMessage());
        }
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Long id, UpdateAccountDto update) {
        Account account = accountRepository.findAccountById(id);
        if (account == null || account.getStatus() == AccountStatus.DELETED) {
            return null;
        }
        account.setPassword(passwordEncoder.encode(update.getPassword()));
        account.setEmail(update.getEmail());
        account.setFullName(update.getFullName());
        account.setAvatar(update.getAvatar());
        account.setPhone(update.getPhone());
        accountRepository.save(account);
        return account;
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) {
            return;
        }
        account.setStatus(AccountStatus.DELETED);
        accountRepository.save(account);
    }

}
