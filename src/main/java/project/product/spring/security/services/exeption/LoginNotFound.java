package project.product.spring.security.services.exeption;
public class LoginNotFound extends RuntimeException {
    public LoginNotFound(String message) {
        super("login not found");
    }
}
