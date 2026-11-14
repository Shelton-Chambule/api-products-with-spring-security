package project.product.spring.security.exception;
public class LoginAlreadyExistsException extends RuntimeException {

    public LoginAlreadyExistsException(String message) {
        super("Try other email, this email already exists");
    }
}
