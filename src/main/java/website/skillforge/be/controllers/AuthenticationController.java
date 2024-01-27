package website.skillforge.be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import website.skillforge.be.entities.Account;
import website.skillforge.be.repository.AccountRepository;
import website.skillforge.be.services.AuthenticationService;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Account account){
        Account newAcoount = authenticationService.register(account);
        return ResponseEntity.ok(newAcoount);
    }
}
