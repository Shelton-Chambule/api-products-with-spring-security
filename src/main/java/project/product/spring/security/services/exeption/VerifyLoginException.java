package project.product.spring.security.services.exeption;
public class VerifyLoginException extends RuntimeException {

    public VerifyLoginException(String message) {
        super("Try other email, this email already exists");
    }
}
