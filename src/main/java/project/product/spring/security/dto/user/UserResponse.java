package project.product.spring.security.dto.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.product.spring.security.entity.User;

@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

    private Long id;
    private String login;

    public UserResponse(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
    }
}
