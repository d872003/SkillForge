package website.skillforge.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import website.skillforge.be.dto.LoginGoogleRequest;
import website.skillforge.be.dto.LoginRequestDTO;
import website.skillforge.be.dto.RegisterRequestDTO;
import website.skillforge.be.entities.Account;
import website.skillforge.be.repository.AccountRepository;
import website.skillforge.be.services.AuthenticationService;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/authentication/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO account){
        Account newAcoount = authenticationService.register(account);
        return ResponseEntity.ok(newAcoount);
    }
    @PostMapping("/authentication/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO account){
        Account newAcoount = authenticationService.login(account);
        return ResponseEntity.ok(newAcoount);
    }
    @PostMapping("/authentication/loginGoogle")
    public ResponseEntity loginGoogle(@RequestBody LoginGoogleRequest account){
        return ResponseEntity.ok(authenticationService.loginGoogle(account.getToken()));
    }
}
