package project.product.spring.security.exception;
public class LoginNotFound extends RuntimeException {
    public LoginNotFound(String message) {
        super("login not found");
    }
}
