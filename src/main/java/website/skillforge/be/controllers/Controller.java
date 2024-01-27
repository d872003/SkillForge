package website.skillforge.be.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("test")
    public ResponseEntity index(){
        return  ResponseEntity.ok("oke");
    }
}
