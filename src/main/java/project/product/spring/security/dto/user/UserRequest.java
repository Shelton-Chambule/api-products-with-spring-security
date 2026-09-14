package project.product.spring.security.dto.user;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entities.User;
import project.product.spring.security.entities.UserRole;

@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

    @NotBlank(message = "Required filed")
    private String login;

    @NotBlank(message = "Required filed")
    private String password;
    private UserRole role;

    public UserRequest(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

}
