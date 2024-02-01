package website.skillforge.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import website.skillforge.be.exception.exceptions.AuthenticationException;

@ControllerAdvice
public class AuthenticationHandleException {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> duplicate(AuthenticationException exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
