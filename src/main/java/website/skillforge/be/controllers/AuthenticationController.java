package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.skillforge.be.dto.authenticationDTO.LoginGoogleRequest;
import website.skillforge.be.dto.authenticationDTO.LoginRequestDTO;
import website.skillforge.be.dto.authenticationDTO.LoginResponseDTO;
import website.skillforge.be.dto.authenticationDTO.RegisterRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.services.AuthenticationService;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/authentication/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO account) {
        Account newAcoount = authenticationService.register(account);
        return ResponseEntity.ok(newAcoount);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO account) {
        LoginResponseDTO newAcoount = authenticationService.login(account);
        return ResponseEntity.ok(newAcoount);
    }

    @PostMapping("/authentication/loginGoogle")
    public ResponseEntity loginGoogle(@RequestBody LoginGoogleRequest account) {
        return ResponseEntity.ok(authenticationService.loginGoogle(account.getToken()));
    }

    @GetMapping("/authentication/getAccountById")
    public ResponseEntity getAccountById(@RequestParam Long id) {
        return ResponseEntity.ok(authenticationService.getAccountById(id));
    }

    @GetMapping("/authentication/getAllAccounts")
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.ok(authenticationService.getAllAccounts());
    }

    @DeleteMapping("/authentication/deleteAccount")
    public ResponseEntity deleteAccount(@RequestParam Long id) {
        authenticationService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted");
    }

    @PutMapping("/authentication/updateAccount")
    public ResponseEntity updateAccount(@RequestParam Long id, @RequestBody RegisterRequestDTO account) {
        return ResponseEntity.ok(authenticationService.updateAccount(id, account));
    }
}
