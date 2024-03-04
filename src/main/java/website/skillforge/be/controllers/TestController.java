package website.skillforge.be.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import website.skillforge.be.entities.Account;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class TestController {


    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity test() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(account);
    }
}
