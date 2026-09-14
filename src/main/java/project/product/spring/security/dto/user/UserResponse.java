package project.product.spring.security.dto.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entities.User;

@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

    private Long id;
    private String login;
    private String password;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }
}
