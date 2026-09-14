package project.product.spring.security.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.product.spring.security.services.exeption.LoginNotFound;
import project.product.spring.security.services.exeption.ResourceNotFound;
import project.product.spring.security.services.exeption.VerifyLoginException;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginNotFound.class)
    public ResponseEntity<StandardError>  loginNotFound(LoginNotFound loginNotFound, HttpServletRequest request){
        StandardError standardError = new StandardError();
        standardError.setError("unauthorized");
        standardError.setMessage(loginNotFound.getMessage());
        standardError.setStatus(HttpStatus.UNAUTHORIZED.value());
        standardError.setTimeStamp(Instant.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<StandardError>  resourceNotFound(ResourceNotFound resourceNotFound, HttpServletRequest request){
        StandardError standardError = new StandardError();
        standardError.setError("resource not found");
        standardError.setMessage(resourceNotFound.getMessage());
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setTimeStamp(Instant.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(VerifyLoginException.class)
    public ResponseEntity<StandardError>  verifyLogin(VerifyLoginException verifyLoginException, HttpServletRequest request){
        StandardError standardError = new StandardError();
        standardError.setError("conflict");
        standardError.setMessage(verifyLoginException.getMessage());
        standardError.setStatus(HttpStatus.CONFLICT.value());
        standardError.setTimeStamp(Instant.now());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
    }

}
